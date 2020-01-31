package testcore.api.modules;

import agent.IAgent;
import central.Configuration;
import io.restassured.response.Response;
import org.codehaus.jettison.json.JSONObject;
import org.testng.Assert;
import testcore.api.BaseApi;
import testcore.api.Builders.TextToSpeechBuilder;
import utils.APIUtils;

import java.util.HashMap;
import java.util.Map;

public class SlangHome extends BaseApi {

    private String text2Intent = "/v1.1/applications/b5bd64e1a8524abfa4b276bdc6ed3912/text2intent?env=prod";

    public SlangHome(Configuration config, IAgent agent, Map<String, String> testData) throws Exception {
        super(config, agent, testData);
    }

    private SlangHome thisClass() throws Exception {
        return new SlangHome(getConfig(), getAgent(), getTestData());
    }


    public SlangHome validateSpeechText() throws Exception {
        String auth = "Basic YzgwNTI1ZGQ1ZmExNDZkNmEzYTFhYmE5MWZjNWQ2Yjk6bm90aGluZw==";
        HashMap<String, String> headers = new HashMap<String, String>() {{
            put("Authorization", auth);
            put("Accept", "application/json");
            put("Accept-Encoding", "gzip, deflate, br");
            put("Accept-Language", "en-US,en;q=0.9,hi;q=0.8");
            put("Content-Type", "application/json"); }};

        TextToSpeechBuilder textToSpeech = new TextToSpeechBuilder();
        JSONObject jsonObject = textToSpeech
                .add("textRequest", "text", "What is vax")
                .add("textRequest;header", "me", "here")
                .build();

        Response response = APIUtils.post(text2Intent, jsonObject.toString(), headers);
        Assert.assertEquals(response.statusCode(), 200);



        /*
        * DefaultHttpClient ht = new DefaultHttpClient();
            HttpPost post = new HttpPost("https://infer.slanglabs.in/v1.1/applications/b5bd64e1a8524abfa4b276bdc6ed3912/text2intent?env=prod");

            post.addHeader("Content-Type", "application/json");
            post.addHeader("Authorization", "Basic YzgwNTI1ZGQ1ZmExNDZkNmEzYTFhYmE5MWZjNWQ2Yjk6bm90aGluZw==");
            post.addHeader("Accept", "application/json");
            post.addHeader("Accept-Encoding", "gzip, deflate, br");
            post.addHeader("Accept-Language", "en-US,en;q=0.9,hi;q=0.8");

            StringEntity userEntity = new StringEntity(jsonBody);
            post.setEntity(userEntity);

            HttpResponse res = ht.execute(post);
            String d = EntityUtils.toString(res.getEntity());
        *
        * */

        return thisClass();
    }

}
