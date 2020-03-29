package testcore.api.Builders;

import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.json.JSONTokener;
import org.testng.Assert;


public class AllObjTypesBuilder extends BaseBuilder {

    private JSONObject jsonObject = new JSONObject();


    //Default objects to be added
    public AllObjTypesBuilder() throws Exception {
        jsonObject.put("firstName", "John");
        jsonObject.put("address", new JSONObject().put("state", "NY").put("postalCode", 12121));
        jsonObject.put("phoneNumbers", new JSONArray()
                .put(new JSONObject().put("type", "home").put("number", "123-111"))
                .put(new JSONObject().put("type", "fax").put("number", "123-222")));
    }

    public JSONObject build() throws Exception {
        return jsonObject;
    }

    /*-----------default methods-----------------*/


    /*Builder Default methods - to be added to base builder*/
    public AllObjTypesBuilder add(String key, Object value) throws Exception {
        jsonObject.put(key, value);
        return this;
    }

    public AllObjTypesBuilder addViaJsonPath(String jsonPath, String key,  Object value) throws Exception {
        DocumentContext doc = JsonPath.parse(jsonObject.toString()).put(jsonPath, key, value);
        String js = new GsonBuilder().create().toJsonTree(doc.json()).getAsJsonObject().toString();
        jsonObject = new JSONObject(js);
        return this;
    }

    public AllObjTypesBuilder updateViaJsonPath(String jsonPath, Object value) throws Exception {
        DocumentContext doc = JsonPath.parse(jsonObject.toString()).set(jsonPath, value);
        String js = new GsonBuilder().create().toJsonTree(doc.json()).getAsJsonObject().toString();
        jsonObject = new JSONObject(js);
        return this;
    }
}
