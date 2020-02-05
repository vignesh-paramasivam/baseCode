package testcore.pages.Home.Steps;

import agent.IAgent;
import central.Configuration;
import in.co.gauravtiwari.voice.client.VoiceAutomationClient;
import in.co.gauravtiwari.voice.clientresources.Voice;
import in.co.gauravtiwari.voice.design.Language;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testcore.pages.Home.HomePage;

import java.util.Map;

public class HomePageSteps extends HomePage {


	public HomePageSteps(Configuration conf, IAgent agent, Map<String, String> testData) throws Exception {
		super(conf, agent, testData);
//		assertPageLoad();
	}

	@Override
	public String pageName() {
		return HomePageSteps.class.getSimpleName();
	}


	public String validateVoiceNavigation() throws Exception {
		Thread.sleep(10000);
		getControl("micButton").click();
		Thread.sleep(5000);
		return HomePageSteps.class.getSimpleName();
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
