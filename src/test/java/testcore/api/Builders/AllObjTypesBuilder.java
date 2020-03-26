package testcore.api.Builders;

import com.jayway.jsonpath.JsonPath;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
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

    public AllObjTypesBuilder add(String key_parent, String name, Object value) throws Exception {
        String[] keys = key_parent.split("//|");

        Object obj = jsonObject;

        for (String key: keys){
            obj = getRequiredObject(key, obj);
        }

        return this;
    }

    private Object getRequiredObject(String key, Object obj) {
        String[] keyWithFilterValues = key.split("\\{");
        String keyToFetch = keyWithFilterValues[0];



        if(isJsonArray(obj)) {
                getObjFromJsonAry();
        } else {
            ((JSONObject)obj).opt(keyToFetch);
        }
    }


    private Object getObjFromJsonAry(String key) {

    }

    private Object getObjFromJsonObj(String key) {

    }


    private boolean isJsonArray(Object obj) {
        Object thisObj = new JSONTokener(obj.toString()).nextValue();
        return thisObj instanceof JSONArray;
    }

    private void addItemToJson(Object jObj, String key, Object value) throws Exception {
        JSONObject jObjInstance;
        JSONArray jArrInstance;

        if(isJsonArray(jObj)) {
            jArrInstance = (JSONArray) jObj;
            jArrInstance.put(value);
        } else {
            jObjInstance = (JSONObject) jObj;
            jObjInstance.put(key, value);
        }
    }

}
