package testcore.api.Builders;
import com.jayway.jsonpath.JsonPath;
import org.codehaus.jettison.json.JSONObject;


public class BaseBuilder {

    private JSONObject jsonObject;

    protected BaseBuilder(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    protected BaseBuilder() {

    }

    public BaseBuilder add(String key, Object value) throws Exception {
        jsonObject.put(key, value);
        return this;
    }

    public BaseBuilder add(String key, JSONObject jsonObject) throws Exception {
        jsonObject.put(key, jsonObject);
        return this;
    }

    public BaseBuilder add(String key_parent, String key, Object value) throws Exception {
        String[] keys = key_parent.split("\\|");
        JSONObject jObj = jsonObject;
        for(String _key: keys) {
            jObj = jObj.getJSONObject(_key);
        }
        jObj.put(key, value);
        return this;
    }

    public Object readRequiredObjectValues(String jsonString, String jsonPath) {
        return JsonPath.read(jsonString, jsonPath);
    }
}
