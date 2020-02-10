package testcore.pages.Home.Steps;

import agent.IAgent;
import central.Configuration;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import testcore.pages.Home.HomePage;

import java.util.Map;

public class HomePageSteps extends HomePage {


	public HomePageSteps(Configuration conf, IAgent agent, Map<String, String> testData) throws Exception {
		super(conf, agent, testData);
	}

	@Override
	public String pageName() {
		return HomePageSteps.class.getSimpleName();
	}


	public void validateVoiceNavigation() throws Exception {
		byte[] data;
		String[] texts = new String[] {"Help", "Check my balance", "Enable 10 days international roaming pack to USA"};

		boolean firstTry = true;

		for(String text: texts) {
			data = Base64.encodeBase64(text.getBytes());
			getAgent().getMobileDriver().pushFile("/storage/emulated/0/SlangE2Etest.txt", data);
			staticWait(2000);

			if(firstTry) {
				WebElement micPopup = getAgent().getMobileDriver().findElement(By.id("slang_lib_bubble_description"));
				micPopup.click();
				takeSnapShot();
				getControl("langSelectNext").click();
				takeSnapShot();
				staticWait(7000);
				Assert.assertTrue(getControl("helpDialog").isVisible());
				firstTry = false;
				continue;
			}

			takeSnapShot();
			waitForMicButton();
			getControl("micButton").click();
			takeSnapShot();
			staticWait(7000);
			Assert.assertTrue(getControl("pageContainer").isVisible());
		}
	}

	public void validateVoiceNavigationHindi() throws Exception {
		byte[] data;
		String[] texts = new String[] {"मदद", "मेरा संतुलन जांचें", "यूएसए में 10 दिनों के अंतर्राष्ट्रीय रोमिंग पैक को सक्षम करें"};

		boolean firstTry = true;

		for(String text: texts) {
			data = Base64.encodeBase64(text.getBytes());
			getAgent().getMobileDriver().pushFile("/storage/emulated/0/SlangE2Etest.txt", data);
			staticWait(2000);

			if(firstTry) {
				WebElement micPopup = getAgent().getMobileDriver().findElement(By.id("slang_lib_bubble_description"));
				micPopup.click();
				takeSnapShot();
				getControls("langHindi").get(1).click();
				takeSnapShot();
				waitForMicButton();
				getControl("micButton").click();
				takeSnapShot();
				staticWait(7000);
				Assert.assertTrue(getControl("helpDialog").isVisible());
				firstTry = false;
				continue;
			}

			takeSnapShot();
			waitForMicButton();
			getControl("micButton").click();
			takeSnapShot();
			staticWait(7000);
			Assert.assertTrue(getControl("pageContainer").isVisible());
			takeSnapShot();
		}
	}

	private void waitForMicPopup() throws Exception {
		waiter().until(ExpectedConditions.elementToBeClickable(By.id("in.slanglabs.airtelmock:id/slang_lib_bubble_description")));
	}

	private void waitForMicButton() throws Exception {
		waiter().until(ExpectedConditions.elementToBeClickable(By.id("in.slanglabs.airtelmock:id/slang_lib_slang_button_v2")));
	}

	private void staticWait(int time) throws Exception {
		Thread.sleep(time);
	}
}
