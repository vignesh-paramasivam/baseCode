package testcore.pages.desktop;

import agent.IAgent;
import central.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import testcore.controls.common.MenuControl;
import testcore.pages.*;

import java.util.Map;

public class DesktopHomePage extends HomePage {

    public DesktopHomePage(Configuration conf, IAgent agent, Map<String, String> testData) throws Exception {
        super(conf, agent, testData);
        assertPageLoad();
    }

}
