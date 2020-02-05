package testcore.pages.desktop;

import agent.IAgent;
import central.Configuration;
import io.qameta.allure.Step;
import testcore.pages.Home.HomePage;
import testcore.pages.LoginPage;

import java.util.Map;

public class DesktopLoginPage extends LoginPage {

    public DesktopLoginPage(Configuration conf, IAgent agent, Map<String, String> testData) throws Exception {
        super(conf, agent, testData);
        assertPageLoad();
    }

    @Step("Enter the username and password to login")
    @Override
    public HomePage login() throws Exception {
        return new HomePage(getConfig(), getAgent(), getTestData()).createInstance();
    }
}
