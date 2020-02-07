package testcore.pages.Home.Steps;

import agent.IAgent;
import central.Configuration;
import com.google.common.primitives.Bytes;
import in.co.gauravtiwari.voice.client.VoiceAutomationClient;
import in.co.gauravtiwari.voice.clientresources.Voice;
import in.co.gauravtiwari.voice.design.Language;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testcore.pages.Home.HomePage;
import io.appium.java_client.android.*;

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
		String[] texts = new String[] {"Check my balance", "Enable 10 days international roaming pack to USA", "Help"};

		boolean firstTry = true;

		for(String text: texts) {
			data = Base64.encodeBase64(text.getBytes());
			getAgent().getMobileDriver().pushFile("/storage/emulated/0/SlangE2Etest.txt", data);
			Thread.sleep(2000);

			if(firstTry) {
				Thread.sleep(3000);
				getControl("micSetupPopup").click();
				Thread.sleep(3000);
				getControl("langSelectNext").click();
				Thread.sleep(7000);
				firstTry = false;
				continue;
			}

			getControl("micButton").click();
			Thread.sleep(7000);
		}
	}

	public void validateVoiceNavigationHindi() throws Exception {
		byte[] data;
		String[] texts = new String[] {"मेरा संतुलन जांचें", "यूएसए में 10 दिनों के अंतर्राष्ट्रीय रोमिंग पैक को सक्षम करें", "मदद"};

		boolean firstTry = true;

		for(String text: texts) {
			data = Base64.encodeBase64(text.getBytes());
			getAgent().getMobileDriver().pushFile("/storage/emulated/0/SlangE2Etest.txt", data);
			Thread.sleep(2000);

			if(firstTry) {
				Thread.sleep(3000);
				getControl("micSetupPopup").click();
				Thread.sleep(3000);
				getControls("langHindi").get(1).click();
				Thread.sleep(3000);
				getControl("micButton").waitUntilVisible();
				getControl("micButton").click();
				Thread.sleep(7000);
				firstTry = false;
				continue;
			}

			getControl("micButton").waitUntilVisible();
			getControl("micButton").click();
			Thread.sleep(7000);
		}
	}
}
