package testcore.api.modules;

import agent.IAgent;
import central.Configuration;
import io.restassured.response.Response;
import org.testng.Assert;
import testcore.api.BaseApi;
import utils.APIUtils;
import java.util.HashMap;
import java.util.Map;

public class Plans extends BaseApi {

    private String plansEndpoint = "xxxxxxxx";

    public Plans(Configuration config, IAgent agent, Map<String, String> testData) throws Exception {
        super(config, agent, testData);
    }

    private Plans thisClass() throws Exception {
        return new Plans(getConfig(), getAgent(), getTestData());
    }


    public AddToCart listPlans() throws Exception {
        HashMap<String, String> headers = new HashMap<String, String>() {{
            put("Authorization", "Token " + getTestData().get("channelToken")); }};

        Response response = APIUtils.get(plansEndpoint, headers);
        Assert.assertEquals(response.statusCode(), 200);

        return new AddToCart(getConfig(), getAgent(), getTestData());
    }

}
