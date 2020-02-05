package testcore.pages.desktop;

import agent.IAgent;
import central.Configuration;
import testcore.pages.Home.HomePage;

import java.util.Map;

public class DesktopHomePage extends HomePage {

    public DesktopHomePage(Configuration conf, IAgent agent, Map<String, String> testData) throws Exception {
        super(conf, agent, testData);
        assertPageLoad();
    }

}
