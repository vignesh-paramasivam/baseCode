package testcore.pages.Home;

import agent.IAgent;
import central.Configuration;

import in.co.gauravtiwari.voice.client.VoiceAutomationClient;
import in.co.gauravtiwari.voice.clientresources.Voice;
import in.co.gauravtiwari.voice.design.Language;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testcore.pages.BasePage;
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
}
