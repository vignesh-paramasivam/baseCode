package testcore.api.Builders;
import javafx.beans.property.MapProperty;
import org.codehaus.jettison.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class CitizenAuthTokenBuilder extends BaseBuilder {

    private String endPoint = "/user/oauth/token";

    //Body
    private String username = "";
    private String password = "";
    private String grantType = "";
    private String scope = "read";
    private String tenantId = "pb";
    private String userType = "CITIZEN";

    HashMap<String, String> formParams;

    //Default objects to be added
    public CitizenAuthTokenBuilder() throws Exception {
        formParams = new HashMap<String, String>() {{
            put("username", username);
            put("password", password);
            put("grant_type", grantType);
            put("scope", scope);
            put("tenantId", tenantId);
            put("userType", userType);
        }};
    }

    public HashMap<String, String> build() throws Exception {
        return formParams;
    }

    public String getEndPoint() throws Exception {
        return endPoint;
    }

    /*----------default methods--------------*/

    public BaseBuilder add(String key, String value) throws Exception {
        formParams.put(key, value);
        return this;
    }
}
