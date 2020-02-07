package testcore.pages.Home.Steps;

import agent.IAgent;
import central.Configuration;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
		String[] texts = new String[] {"Check my balance", "Enable 10 days international roaming pack to USA", "Help"};

		boolean firstTry = true;

		for(String text: texts) {
			data = Base64.encodeBase64(text.getBytes());
			getAgent().getMobileDriver().pushFile("/storage/emulated/0/SlangE2Etest.txt", data);
			Thread.sleep(2000);

			if(firstTry) {
				getControl("micSetupPopup").click();
				takeSnapShot();
				getControl("langSelectNext").click();
				takeSnapShot();
				Thread.sleep(7000);
				firstTry = false;
				continue;
			}

			takeSnapShot();
			waitForMicButton();
			getControl("micButton").click();
			takeSnapShot();
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
				getControl("micSetupPopup").click();
				takeSnapShot();
				getControls("langHindi").get(1).click();
				takeSnapShot();
				waitForMicButton();
				getControl("micButton").click();
				takeSnapShot();
				Thread.sleep(7000);
				firstTry = false;
				continue;
			}

			takeSnapShot();
			waitForMicButton();
			getControl("micButton").click();
			takeSnapShot();
			Thread.sleep(7000);
			takeSnapShot();
		}
	}

	private void waitForMicButton() throws Exception {
		waiter().until(ExpectedConditions.elementToBeClickable(By.id("in.slanglabs.airtelmock:id/slang_lib_slang_button_v2")));
	}
}
