package testcore.api.modules;

import agent.IAgent;
import central.Configuration;
import io.restassured.response.Response;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import testcore.api.BaseApi;
import utils.APIUtils;

import java.util.HashMap;
import java.util.Map;

public class AddToCart extends BaseApi {

    private String addCartEndpoint = "/order/add_cart";

    public AddToCart(Configuration config, IAgent agent, Map<String, String> testData) throws Exception {
        super(config, agent, testData);
    }

    private AddToCart thisClass() throws Exception {
        return new AddToCart(getConfig(), getAgent(), getTestData());
    }


    public Checkout addToCart() throws Exception {
        HashMap<String, String> headers = new HashMap<String, String>() {{
            put("Authorization", "Token " + getTestData().get("channelToken"));
            put("Content-Type", "application/json"); }};


        String jsonBody = "xxxx";

        Response response = APIUtils.post(addCartEndpoint, jsonBody, headers);
        Assert.assertEquals(response.statusCode(), 200);

        getTestData().put("cart_id", response.path("cart_id"));

        return new Checkout(getConfig(), getAgent(), getTestData());
    }

}
