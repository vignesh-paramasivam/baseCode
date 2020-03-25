package testcore.api.modules;

import agent.IAgent;
import central.Configuration;
import io.restassured.response.Response;
import org.codehaus.jettison.json.JSONObject;
import org.testng.Assert;
import testcore.api.BaseApi;
import testcore.api.Builders.CitizenAuthTokenBuilder;
import testcore.api.Builders.CitizenComplaintCreationBuilder;
import utils.APIUtils;

import java.util.HashMap;
import java.util.Map;

public class CreateCitizenAuthToken extends BaseApi {

    //Headers
    private String origin = "https://egov-micro-qa.egovernments.org";
    private String acceptEncoding = "gzip, deflate, br";
    private String acceptLanguage = "en-US,en;q=0.9";
    private String authorization = "Basic ZWdvdi11c2VyLWNsaWVudDplZ292LXVzZXItc2VjcmV0";
    private String accept = "application/json, text/plain, */*";
    private String authority = "egov-micro-qa.egovernments.org";

    public CreateCitizenAuthToken(Configuration config, IAgent agent, Map<String, String> testData) throws Exception {
        super(config, agent, testData);
    }

    @Override
    public String pageName() {
        return BaseApi.class.getSimpleName();
    }

    private CreateCitizenAuthToken thisClass() throws Exception {
        return new CreateCitizenAuthToken(getConfig(), getAgent(), getTestData());
    }

    public CreateCitizenAuthToken generateAuthToken() throws Exception {

        HashMap<String, String> headers = new HashMap<String, String>() {{
            put("origin", origin);
            put("accept-encoding", acceptEncoding);
            put("accept-language", acceptLanguage);
            put("Authorization", authorization);
            put("Content-Type", "application/x-www-form-urlencoded");
            put( "accept", accept);
            put( "referer", "https://egov-micro-qa.egovernments.org/citizen/user/otp");
            put("Authority", authority);
        }};

        CitizenAuthTokenBuilder builder = new CitizenAuthTokenBuilder();
        builder.add("username", "7829727713")
        .add("password", "123456")
        .add("grant_type", "password");

        Response response = APIUtils.post(builder.getEndPoint(), builder.build(), headers);

        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        String access_token = jsonObject.getString("access_token");

        getTestData().put("access_token", access_token);

        return thisClass();
    }


    public CreateCitizenAuthToken createComplaint() throws Exception {

        HashMap<String, String> headers = new HashMap<String, String>() {{
            put("origin", origin);
            put("accept-encoding", acceptEncoding);
            put("accept-language", acceptLanguage);
            put("Content-Type", "application/json;charset=UTF-8");
            put( "accept", accept);
            put( "referer", "https://egov-micro-qa.egovernments.org/citizen/add-complaint");
            put("authority", authority);
        }};

        CitizenComplaintCreationBuilder builder = new CitizenComplaintCreationBuilder();

        builder.add("RequestInfo", "authToken", getTestData().get("access_token"));
        builder.add("services", "source", "web");
        //Refer todo-1 : only identifying 1st object as of now. Here, key is null/ not considered as the value is added as a object in array
        builder.add("actionInfo|media", "", "mediaVal");

        builder.add("services|addressDetail{landmark:123}", "testMe", "testVal");

        Response response = APIUtils.post(builder.getEndPoint(), builder.build().toString(), headers);

        //With the given jsonBody - assertion will fail - will be addressed after fixing the json body construct
        Assert.assertEquals(response.getStatusCode(), 201);

        return thisClass();
    }




}
