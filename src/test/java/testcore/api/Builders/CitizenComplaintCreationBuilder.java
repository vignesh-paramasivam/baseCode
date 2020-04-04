package testcore.api.Builders;

import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;


public class CitizenComplaintCreationBuilder extends BaseBuilder {

    private String endPoint = "/rainmaker-pgr/v1/requests/_create?tenantId=pb";

    private JSONObject requestInfo;
    private JSONArray actionInfo;
    private JSONArray services;
    private JSONObject jsonObject = new JSONObject();

    //Default objects to be added
    public CitizenComplaintCreationBuilder() throws Exception {
        jsonObject.put("RequestInfo", requestInfo());
        jsonObject.put("actionInfo", actionInfo());
        jsonObject.put("services", services());
    }


    public JSONObject getRequestInfo() {
        return requestInfo;
    }

    public JSONArray getActionInfo() {
        return actionInfo;
    }

    public JSONArray getServices() {
        return services;
    }

    private JSONObject requestInfo() throws Exception {
        requestInfo = new JSONObject();

        requestInfo.put("apiId", "Rainmaker");
        requestInfo.put("ver", ".01");
        requestInfo.put("ts", "");
        requestInfo.put( "action", "_create");
        requestInfo.put( "did", "1");
        requestInfo.put("key", "");
        requestInfo.put("msgId", "20170310130900|en_IN");
        requestInfo.put("authToken", "");

        return requestInfo;
    }

    private JSONArray actionInfo() throws Exception {
        actionInfo = new JSONArray();
        actionInfo.put(new JSONObject()
                .put("media", new JSONArray()));

        return actionInfo;
    }

    private JSONArray services() throws Exception {
        services = new JSONArray();

        services.put(new JSONObject()
                .put("serviceCode", "GarbageNeedsTobeCleared")
                .put("description", "test")
                .put("addressDetail",new JSONObject()
                                .put("latitude", "21.322422")
                                .put("longitude", "21.573419")
                                .put("city", "bangalore")
                                .put("mohalla", "JLC216")
                                .put("houseNoAndStreetName", "123")
                                .put("landmark", "123")
                )
                .put("address", "Jalandhar,Nakodar Rd, Shaheed Udham Singh Nagar, Jalandhar, Punjab 144001, India")
                .put("tenantId", "pb.jalandhar")
                .put("source", "web")
                .put("phone", "7829727713")
        );

        return services;
    }

    public JSONObject build() throws Exception {
        return jsonObject;
    }

    public String getEndPoint() throws Exception {
        return endPoint;
    }


    /*-----------default methods-----------------*/


    /*Builder Default methods - to be added to base builder*/
    public CitizenComplaintCreationBuilder add(String key, Object value) throws Exception {
        jsonObject.put(key, value);
        return this;
    }

    public CitizenComplaintCreationBuilder addViaJsonPath(String jsonPath, String key,  Object value) throws Exception {
        DocumentContext doc = JsonPath.parse(jsonObject.toString()).put(jsonPath, key, value);
        String js = new GsonBuilder().create().toJsonTree(doc.json()).getAsJsonObject().toString();
        jsonObject = new JSONObject(js);
        return this;
    }

    public CitizenComplaintCreationBuilder updateViaJsonPath(String jsonPath, Object value) throws Exception {
        DocumentContext doc = JsonPath.parse(jsonObject.toString()).set(jsonPath, value);
        String js = new GsonBuilder().create().toJsonTree(doc.json()).getAsJsonObject().toString();
        jsonObject = new JSONObject(js);
        return this;
    }
}
