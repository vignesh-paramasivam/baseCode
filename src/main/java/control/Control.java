package control;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import agent.IAgent;
import central.AutomationCentral;
import enums.Platform;
import page.IPage;
import pagedef.Identifier;
import testcore.controls.common.*;
import utils.JavaUtils;

public abstract class Control implements IControl {
	protected static Logger logger = AutomationCentral.getLogger();
	private String name;
	private IPage page = null;
	private IAgent agent = null;
	private Platform platform = null;
	private WebElement webElement = null;

	public Control(String name, IPage page, WebElement element) {
		this.setName(name);
		this.setPage(page);
		this.setAgent(page.getAgent());
		this.setPlatform(page.getPlatform());
		this.setRawWebElement(element);
	}

	private void setName(String name) {
		this.name = name;
	}

	protected String getName() {
		return this.name;
	}

	protected WebElement getRawWebElement() {
		return webElement;
	}

	private void setRawWebElement(WebElement wrappedElement) {
		this.webElement = wrappedElement;
	}

	protected IAgent getAgent() {
		return agent;
	}

	private void setAgent(IAgent agent) {
		this.agent = agent;
	}

	private static String getControlInfoString(IPage page, String name, Identifier by) {
		//		return String.format("Platform: %s, Page: %s, Control Name: %s, Locator Type: %s, Locator Value: %s",
		//				page.getPlatform(), page.getClass().getSimpleName(), name, by.getIdType(), by.getValue());
		return String.format("Locator Type: %s, Locator Value: %s, Page: %s", by.getIdType(), by.getValue(), page.getClass().getSimpleName());
	}

	protected String getControlBasicInfoString() {
		return String.format("Platform: %s, Page: %s, Control Name: %s", page.getPlatform(),
				page.getClass().getSimpleName(), this.getName());
	}

	protected static void throwControlCreationException(String msg, Exception e) throws Exception {
		String emsg = String.format("No such element found with %s. Exception: %s", msg, e.getMessage());
		logger.debug(emsg);
		throw new Exception(emsg);
	}

	protected void throwChildFetchException(String msg, Exception e) throws Exception {
		String emsg = String.format("Failure in %s. Exception: %s", msg, e.getMessage());
		logger.error(emsg);
		throw new Exception(emsg);
	}

	protected void throwControlActionException(Exception e) throws Exception {
		String emsg = String.format("Issue in %s for %s. Exception: %s", JavaUtils.getCaller(),
				this.getControlBasicInfoString(), e.getMessage());
		logger.error(emsg);
		throw new Exception(emsg);
	}

	public static IControl createControl(IPage page, String name, Identifier by) throws Exception {
		//		String msg = "Creating Control => " + getControlInfoString(page, name, by);
		//		logger.info("createControl - START");
		String msg = getControlInfoString(page, name, by);
		try {

			return createWebElement(page, name, by);

		} catch (Exception e) {
			//			logger.info("createControl - CATCH");
			//throwControlCreationException(msg, e);
		}

		//logger.info("createControl - END");
		return null;
	}

	private static IControl createWebElement(IPage page, String name, Identifier by) throws Exception {
		return new WebControl(name, page, (WebElement) identify(page.getAgent().getWebDriver(), by).get(0));
	}

	public IControl getControl(String name) throws Exception {
		Identifier id = this.getPage().getIdentifier(name);
		String msg = String.format("Fetching Control %s in %s parent control.", getControlInfoString(page, name, id),
				this.getName());
		try {
			logger.debug(msg);

			return this.findWebElement(name, id);
		} catch (Exception e) {
			throwChildFetchException(msg, e);
		}

		return null;
	}

	private IControl findWebElement(String name, Identifier by) throws Exception {
		return new WebControl(name, page, (WebElement) identify(this.getRawWebElement(), by).get(0));
	}

	public static List<IControl> createControls(IPage page, String name, Identifier by) throws Exception {
		String msg = "Creating Controls => " + getControlInfoString(page, name, by);
		try {
			logger.debug(msg);
			return createWebElements(page, name, by);
		} catch (Exception e) {
			throwControlCreationException(msg, e);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	private static List<IControl> createWebElements(IPage page, String name, Identifier by) throws Exception {
		List<IControl> outList = new ArrayList<IControl>();
		for (WebElement element : (List<WebElement>) identify(page.getAgent().getWebDriver(), by)) {
			outList.add(new WebControl(name, page, element));
		}
		return outList;
	}

	public List<IControl> getControls(String name) throws Exception {
		Identifier id = this.getPage().getIdentifier(name);
		String msg = String.format("Fetching Control %s in %s parent control.", getControlInfoString(page, name, id),
				this.getName());
		return this.findWebElements(name, id);

	}

	@SuppressWarnings("unchecked")
	private List<IControl> findWebElements(String name, Identifier by) throws Exception {
		List<IControl> outList = new ArrayList<IControl>();
		for (WebElement element : (List<WebElement>) identify(this.getRawWebElement(), by)) {
			outList.add(new WebControl(name, page, element));
		}
		return outList;
	}

	private static List<?> identify(SearchContext finder, Identifier by) throws Exception {
		long start = System.currentTimeMillis() / 1000;
		long current = start;
		List<?> elements = null;
		while (current - start < 30) {
			try {
				switch (by.getIdType()) {
					case ID:
						elements = finder.findElements(By.id(by.getValue()));
						break;
					case XPATH:
						elements = finder.findElements(By.xpath(by.getValue()));
						break;
					case NAME:
						elements = finder.findElements(By.name(by.getValue()));
						break;
					case LINKTEXT:
						elements = finder.findElements(By.linkText(by.getValue()));
						break;
					case CLASSNAME:
						elements = finder.findElements(By.className(by.getValue()));
						break;
					case CSS:
						elements = finder.findElements(By.cssSelector(by.getValue()));
						break;
					case PARTIALLINKTEXT:
						elements = finder.findElements(By.partialLinkText(by.getValue()));
						break;
					case TAGNAME:
						elements = finder.findElements(By.tagName(by.getValue()));
						break;
					default:
						String msg = "Wrong identifier passed. Identifier Type : " + by.getIdType();
						logger.error(msg);
						break;
				}
			} catch (Exception e) {
				// ignore. would continue polling.
			}

			if (elements != null) {
				break;
			} else {
				// Sleeping for polling time - 500ms
				Thread.sleep(500);
				current = System.currentTimeMillis() / 1000;
			}
		}

		if (elements == null) {
			String msg = String.format("Control could not be identified with locator type: %s and locator value: %s",
					by.getIdType(), by.getValue());
			logger.error(msg);
			throw new Exception(msg);
		} else {
			return elements;
		}
	}

	public IPage getPage() {
		return page;
	}

	public void setPage(IPage page) {
		this.page = page;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public static IControl createDynamicControl(IPage page, String name, Identifier by, String controlType, WebElement parentElement) throws Exception {
		String msg = getControlInfoString(page, name, by);
		try {
			logger.debug(msg);
			return createDymamicWebElement(page, name, by, controlType, parentElement);
		} catch (Exception e) {
		}
		return null;
	}

	private static IControl createDymamicWebElement(IPage page, String name, Identifier by, String controlType, WebElement parentElement) throws Exception {

		String locatorParameter = name;
		SearchContext finder = parentElement;

		if(finder == null){
			finder = page.getAgent().getWebDriver();
		}

		switch(controlType){
			case "TextboxControl":
				return new TextboxControl(locatorParameter, page, (WebElement) identify(finder, by, locatorParameter).get(0));
			case "ButtonControl":
				return new ButtonControl(locatorParameter, page, (WebElement) identify(finder, by, locatorParameter).get(0));
			case "LinkControl":
				return new LinkControl(locatorParameter, page, (WebElement) identify(finder, by, locatorParameter).get(0));
			case "CheckboxControl":
				return new CheckboxControl(locatorParameter, page, (WebElement) identify(finder, by, locatorParameter).get(0));
			case "CalendarControl":
				return new CalendarControl(locatorParameter, page, (WebElement) identify(finder, by, locatorParameter).get(0));
			case "GridControl":
				return new GridControl(locatorParameter, page, (WebElement) identify(finder, by, locatorParameter).get(0));
			case "TextareaControl":
				return new TextareaControl(locatorParameter, page, (WebElement) identify(finder, by, locatorParameter).get(0));
			case "DropdownControl":
				return new DropdownControl(locatorParameter, page, (WebElement) identify(finder, by, locatorParameter).get(0));
			case "MenuControl":
				return new MenuControl(locatorParameter, page, (WebElement) identify(finder, by, locatorParameter).get(0));
			case "SectionControl":
				return new SectionControl(locatorParameter, page, (WebElement) identify(finder, by, locatorParameter).get(0));
			case "RadioControl":
				return new RadioControl(locatorParameter, page, (WebElement) identify(finder, by, locatorParameter).get(0));
			case "NotificationControl":
				return new NotificationControl(locatorParameter, page, (WebElement) identify(finder, by, locatorParameter).get(0));
			default:
				throw new Error("Invalid control type; fix it under control.Control.java");
		}
	}

	private static List<?> identify(SearchContext finder, Identifier by, String locatorParameter) throws Exception {
		long start = System.currentTimeMillis() / 1000;
		long current = start;
		List<?> elements = null;
		while (current - start < 30) {
			try {
				switch (by.getIdType()) {
					case ID:
						elements = finder.findElements(By.id(by.getValue(locatorParameter)));
						break;
					case XPATH:
						// Locate xpath from given parent element. if the parent element is null, it takes 'document' root as parent.
						String locator_toLocateFromParent = addDotToXpathLocatorPath(by, locatorParameter);
						elements = finder.findElements(By.xpath(locator_toLocateFromParent));
						break;
					case NAME:
						elements = finder.findElements(By.name(by.getValue(locatorParameter)));
						break;
					case LINKTEXT:
						elements = finder.findElements(By.linkText(by.getValue(locatorParameter)));
						break;
					case CLASSNAME:
						elements = finder.findElements(By.className(by.getValue(locatorParameter)));
						break;
					case CSS:
						elements = finder.findElements(By.cssSelector(by.getValue(locatorParameter)));
						break;
					case PARTIALLINKTEXT:
						elements = finder.findElements(By.partialLinkText(by.getValue(locatorParameter)));
						break;
					case TAGNAME:
						elements = finder.findElements(By.tagName(by.getValue(locatorParameter)));
						break;
					default:
						String msg = "Wrong identifier passed. Identifier Type : " + by.getIdType();
						logger.error(msg);
						break;
				}
			} catch (Exception e) {
				// ignore. would continue polling.
			}

			if (elements != null) {
				break;
			} else {
				// Sleeping for polling time - 500ms
				Thread.sleep(500);
				current = System.currentTimeMillis() / 1000;
			}
		}

		if (elements == null) {
			String msg = String.format("Control could not be identified with locator type: %s and locator value: %s",
					by.getIdType(), by.getValue(locatorParameter));
			logger.error(msg);
			throw new Exception(msg);
		} else {
			return elements;
		}
	}

	public int deleteAttachment() throws Exception {
		return 0;
	}

	public void clear_via_javascript() throws Exception {
		throw new Exception("Implemeneted under specific control classes");

	}

	private static String addDotToXpathLocatorPath(Identifier by, String locatorParameter) {
		String[] array_childLocatorVars = (by.getValue(locatorParameter).split("\\|"));

		for (int loc = 0; loc < array_childLocatorVars.length; loc++) {
			array_childLocatorVars[loc] = "." + array_childLocatorVars[loc].trim();
		}
		return StringUtils.join(array_childLocatorVars, " | ");
	}
}



