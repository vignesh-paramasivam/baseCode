package testcore.api.modules;

import agent.IAgent;
import central.Configuration;
import io.restassured.response.Response;
import org.testng.Assert;
import testcore.api.BaseApi;
import utils.APIUtils;

import java.util.HashMap;
import java.util.Map;

public class CreateCitizenAuthToken extends BaseApi {

    public CreateCitizenAuthToken(Configuration config, IAgent agent, Map<String, String> testData) throws Exception {
        super(config, agent, testData);
    }

    @Override
    public String pageName() {
        return BaseApi.class.getSimpleName();
    }

    


}
