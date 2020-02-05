package agent.internal;

import central.Configuration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AndroidWebAgent extends AndroidAgent {

	public AndroidWebAgent(Configuration config, AppiumDriver<MobileElement> driver) throws Exception {
		super(config, driver);
	}

}
