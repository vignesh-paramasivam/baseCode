package testcore.api;

import agent.IAgent;
import central.Configuration;
import page.Page;
import testcore.pages.BasePage;

import java.util.Map;

public abstract class BaseApi extends BasePage {


    public BaseApi(Configuration config, IAgent agent, Map<String, String> testData) throws Exception {
        super(config, agent, testData);
    }

    @Override
    public String pageName() {
        return BaseApi.class.getSimpleName();
    }


}
