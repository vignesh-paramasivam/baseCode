package testcore.api.modules;

import agent.IAgent;
import central.Configuration;
import io.restassured.response.Response;
import org.testng.Assert;
import testcore.api.BaseApi;
import utils.APIUtils;

import java.util.HashMap;
import java.util.Map;

public class Checkout extends BaseApi {

    private String checkoutEndpoint = "xxxxxxxx";

    public Checkout(Configuration config, IAgent agent, Map<String, String> testData) throws Exception {
        super(config, agent, testData);
    }

    private Checkout thisClass() throws Exception {
        return new Checkout(getConfig(), getAgent(), getTestData());
    }


    public Checkout checkout() throws Exception {
        HashMap<String, String> headers = new HashMap<String, String>() {{
            put("Authorization", "Token " + getTestData().get("channelToken"));
            put("Content-Type", "application/json"); }};

        //Used in requestBody
        Object cartId = getTestData().get("cart_id");

        String jsonBody = "xxxxxxxx";

        Response response = APIUtils.post(checkoutEndpoint, jsonBody, headers);
        Assert.assertEquals(response.statusCode(), 200);

        return thisClass();
    }

}
