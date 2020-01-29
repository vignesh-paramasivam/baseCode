package testcore.api.modules;

import agent.IAgent;
import central.Configuration;
import io.restassured.response.Response;
import org.testng.Assert;
import testcore.api.BaseApi;
import utils.APIUtils;

import java.util.HashMap;
import java.util.Map;

public class User extends BaseApi {

    private String userEndpoint = "xxxxxxxxx";
    private String addDeviceTokenEndpoint = "xxxxxxx";

    public User(Configuration config, IAgent agent, Map<String, String> testData) throws Exception {
        super(config, agent, testData);
    }

    private User thisClass() throws Exception {
        return new User(getConfig(), getAgent(), getTestData());
    }


    public User validateUser() throws Exception {

        String openToken = "xxxxxxxxxxxxxxxxxx";
        HashMap<String, String> headers = new HashMap<String, String>() {{
            put("Authorization", "Token " + openToken);
            put("Accept", "application/json");
            put("Content-Type", "application/json"); }};

        Response response = APIUtils.get(userEndpoint, headers);
        Assert.assertEquals(response.statusCode(), 200);

        getTestData().put("channelToken", response.path("channel_token"));

        return thisClass();
    }

    public Plans addDeviceToken() throws Exception {
        String jsonBody = "xxxxxxxxx";

        HashMap<String, String> headers = new HashMap<String, String>() {{
            put("Authorization", "Token xxxxxxxxxxx");
            put("Content-Type", "application/json");
        }};

        Response response = APIUtils.post(addDeviceTokenEndpoint, jsonBody, headers);
        Assert.assertEquals(response.statusCode(), 200);

        return new Plans(getConfig(), getAgent(), getTestData());
    }

}
