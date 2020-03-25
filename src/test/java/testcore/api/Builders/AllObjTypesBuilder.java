package testcore.api.Builders;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.json.JSONTokener;
import org.testng.Assert;


public class AllObjTypesBuilder {

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

    public AllObjTypesBuilder add(String key, JSONObject jsonObject) throws Exception {
        this.jsonObject.put(key, jsonObject);
        return this;
    }

    public AllObjTypesBuilder add(String key_parent, String key, Object value) throws Exception {
        identifyRequiredObject(key_parent);
        return this;
    }

    private Object identifyRequiredObject(String key_parent) {
        String[] keys = key_parent.split("\\|");
        return new Object();
    }

}
