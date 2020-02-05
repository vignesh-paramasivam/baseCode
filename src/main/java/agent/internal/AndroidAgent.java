/**
 * 
 */
package agent.internal;

import central.Configuration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public abstract class AndroidAgent extends MobileAgent {
	public AndroidAgent(Configuration config, AppiumDriver<MobileElement> driver) throws Exception {
		super(config, driver);
	}

}
