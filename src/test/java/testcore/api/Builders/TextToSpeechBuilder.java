package testcore.api.Builders;

import org.codehaus.jettison.json.JSONObject;
import javax.json.Json;
import javax.json.JsonObject;

public class TextToSpeechBuilder {

    private JSONObject headerObj;
    private JSONObject txtReqObj;
    public final JSONObject jsonObject = new JSONObject();

    //Default objects to be added
    public TextToSpeechBuilder() throws Exception {
        jsonObject.put("type", "REQUEST_TEXT");
        jsonObject.put("textRequest", textReqObj());
        jsonObject.getJSONObject("textRequest").put("header", headerObj());
    }

    /*Builder Default methods - to be added to base builder*/
    public TextToSpeechBuilder add(String key, Object value) throws Exception {
        jsonObject.put(key, value);
        return this;
    }

    public TextToSpeechBuilder add(String key, JSONObject jsonObject) throws Exception {
        jsonObject.put(key, jsonObject);
        return this;
    }

    public TextToSpeechBuilder add(String key_parent, String key, Object value) throws Exception {
        String[] keys = key_parent.split("\\|");
        JSONObject jObj = jsonObject;
        for(String _key: keys) {
           jObj = jObj.getJSONObject(_key);
        }
        jObj.put(key, value);
        return this;
    }


    /*getters to be revisited as to access elements added during the instance*/
    public JSONObject getHeaderObj() {
        return headerObj;
    }

    public JSONObject getTextReqObj() {
        return txtReqObj;
    }

    public JSONObject build() throws Exception {
        return jsonObject;
    }

    private JSONObject textReqObj() throws Exception {
        txtReqObj = new JSONObject();
        txtReqObj.put("header" , "");
        txtReqObj.put("text", "");

        return txtReqObj;
    }


    private JSONObject headerObj() throws Exception {
        headerObj = new JSONObject();
        long ts = 1580364042224L;
        JSONObject ct = new JSONObject();

        headerObj.put("appId", "b5bd64e1a8524abfa4b276bdc6ed3912");
        headerObj.put("requestId", "b4ca9619-76a0-49b6-80b7-00aa0cf519a5");
        headerObj.put("timestamp", ts);
        headerObj.put( "language", "en-IN");
        headerObj.put( "sessionId", "b147c3fa-88d1-492b-b6fc-df65f7d492fd");
        headerObj.put("authToken", "c80525dd5fa146d6a3a1aba91fc5d6b9");
        headerObj.put("context", ct);

        return headerObj;
    }
    
}
