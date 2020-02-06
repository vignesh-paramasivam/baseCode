package agent.internal;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public interface IDriverActions {

	AndroidDriver<MobileElement> getMobileDriver() throws Exception;

	WebDriverWait getWaiter();

	WebDriver getWebDriver() throws Exception;

	void quit();

}
