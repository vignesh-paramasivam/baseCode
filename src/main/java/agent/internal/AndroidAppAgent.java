package agent.internal;

import central.Configuration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AndroidAppAgent extends AndroidAgent {

	public AndroidAppAgent(Configuration config, AppiumDriver<MobileElement> driver) throws Exception {
		super(config, driver);
	}

	public void goTo(String url) throws Exception {
		throwUnsupportedActionException();
	}

	public void goToHome() throws Exception {
		throwUnsupportedActionException();
	}

}
