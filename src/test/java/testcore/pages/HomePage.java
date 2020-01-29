package testcore.pages;

import agent.IAgent;
import central.Configuration;

import in.co.gauravtiwari.voice.client.VoiceAutomationClient;
import in.co.gauravtiwari.voice.clientresources.Voice;
import in.co.gauravtiwari.voice.design.Language;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testcore.pages.desktop.DesktopHomePage;

import java.util.Map;

public class HomePage extends BasePage {



	public HomePage(Configuration conf, IAgent agent, Map<String, String> testData) throws Exception {
		super(conf, agent, testData);
	}

	public HomePage createInstance() throws Exception{
		logger.info(String.format("started with the create instance"));
		logger.info(getPlatform().toString());
		HomePage derivedHomePage;
		switch(getPlatform()){
		case DESKTOP_WEB:
			derivedHomePage = new DesktopHomePage(getConfig(),getAgent(),getTestData());
			break;
		default:
			throw new NotImplementedException("Invalid platform - please check the platform argument: " + getPlatform().toString()) ;
		}
		return derivedHomePage;
	}

	@Override
	public String pageName() {
		return HomePage.class.getSimpleName();
	}

	public void voiceInput() throws Exception {
		WebDriver driver = getAgent().getWebDriver();
		driver.get("https://slanglabs.in/");

		Thread.sleep(3000);
		//Trigger Voice Assistant
		driver.findElement(By.cssSelector("img[alt='Trigger Voice Assistant']")).click();

		Thread.sleep(5000);

		//http://DESKTOP-FOCIQGG:9090/
		System.setProperty("VoiceAutomationServerEndpoint","http://DESKTOP-FOCIQGG:9090/");
		//API key - 754b900bb0284b13a860ad55ec4d052a

		System.setProperty("VoiceRssKey", "754b900bb0284b13a860ad55ec4d052a");
		Voice voice = new Voice("What is vax", Language.ENGLISH_INDIA);
		VoiceAutomationClient voiceAutomationClient = new VoiceAutomationClient();
		voiceAutomationClient.load(voice);

		int c = 0;
		while(c < 5) {
			voiceAutomationClient.play(voice);
			Thread.sleep(1000);
			c++;
		}


		Thread.sleep(5000);
	}

}
