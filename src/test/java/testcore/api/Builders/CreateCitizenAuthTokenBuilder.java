package testcore.api.Builders;
import org.codehaus.jettison.json.JSONObject;


public class CreateCitizenAuthTokenBuilder extends BaseBuilder {

    private JSONObject headerObj;
    private final JSONObject jsonObject = new JSONObject();

    //Headers
    private String origin = "https://egov-micro-qa.egovernments.org";
    private String acceptEncoding = "gzip, deflate, br";
    private String acceptLanguage = "en-US,en;q=0.9";
    private String authorization = "Basic ZWdvdi11c2VyLWNsaWVudDplZ292LXVzZXItc2VjcmV0";
    private String contentType = "application/x-www-form-urlencoded";
    private String accept = "application/json, text/plain, */*";
    private String referer = "https://egov-micro-qa.egovernments.org/citizen/user/otp";
    private String authority = "egov-micro-qa.egovernments.org";
    private String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36";

    //Body
    private String username = "";
    private String password = "";
    private String grantType = "";
    private String scope = "read";
    private String tenantId = "pb";
    private String userType = "CITIZEN";



    //Default objects to be added
    public CreateCitizenAuthTokenBuilder(JSONObject jsonObject) throws Exception {
        super(jsonObject);
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        jsonObject.put("grant-type", grantType);
        jsonObject.put("scope", scope);
        jsonObject.put("tenantId", tenantId);
        jsonObject.put("userType", userType);
    }

    public JSONObject getHeaders() throws Exception {
        return headerObj();
    }

    public JSONObject build() throws Exception {
        return jsonObject;
    }

    private JSONObject headerObj() throws Exception {
        headerObj = new JSONObject();

        headerObj.put("origin", origin);
        headerObj.put("accept-encoding", acceptEncoding);
        headerObj.put("accept-language", acceptLanguage);
        headerObj.put("authorization", authorization);
        headerObj.put("contentType", contentType);
        headerObj.put( "accept", accept);
        headerObj.put( "referer", referer);
        headerObj.put("authority", authority);
        headerObj.put("userAgent", userAgent);

        return headerObj;
    }

}
