package testcore.api.Builders;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.json.JSONTokener;
import org.testng.Assert;


public class CitizenComplaintCreationBuilder {

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
                .put("media", new JSONArray()))
                //Added for testing
                .put(new JSONObject()
                        .put("role", new JSONArray()
                                .put(new JSONArray().put(new JSONObject()
                                        .put("a", 1)
                                        .put("b", 2)))
                                .put(new JSONArray().put(new JSONObject()
                                        .put("c", 3)
                                        .put("d", 4)))));

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

    public CitizenComplaintCreationBuilder add(String key, JSONObject jsonObject) throws Exception {
        this.jsonObject.put(key, jsonObject);
        return this;
    }


    //If JSONArray value is getting added the Key can be null
    public CitizenComplaintCreationBuilder add(String key_parent, String key, Object value) throws Exception {
        String[] keys = key_parent.split("\\|");

        Object jObj = jsonObject;

        for(String _key: keys) {
            // Check if object is JSON array
            String[] keyCheck = _key.split("\\{");
            //TODO 1: Accessing json arrays inside json array to be done
            if(isJsonArray(jObj)){
                if(keyCheck.length > 1) {
                    jObj = getKeyInDepth(_key, jObj);
                } else {
                    /*Todo: identify objects in depth instead of first object*/
                   jObj = ((JSONObject)((JSONArray) jObj).get(0)).getJSONArray(_key);
                }
            }
            else if(((JSONObject) jObj).optJSONArray(keyCheck[0]) != null) {
                //if we need to get the object inside json array
                if(keyCheck.length > 1) {
                    jObj = getKeyInDepth(_key, jObj);
                } else {
                    jObj = ((JSONObject) jObj).getJSONArray(_key); }
            } else if (((JSONObject) jObj).optJSONObject(_key) != null) {
                jObj = ((JSONObject) jObj).getJSONObject(_key); }
            else {
                Assert.fail(_key + "object is not available in the json");
            }
        }

        addItemToJson(jObj, key, value);

        return this;
    }

    private void addItemToJson(Object jObj, String key, Object value) throws Exception {
        JSONObject jObjInstance;
        JSONArray jArrInstance;

        if(isJsonArray(jObj)) {
            //TODO 2: May get class cast exception - need to address
            jArrInstance = (JSONArray) jObj;
            jArrInstance.put(value);
        } else {
            jObjInstance = (JSONObject) jObj;
            jObjInstance.put(key, value);
        }
    }

    private boolean isJsonArray(Object obj) throws Exception {
        Object thisObj = new JSONTokener(obj.toString()).nextValue();
        return thisObj instanceof JSONArray;
    }

    private Object getKeyInDepth(String _key, Object Obj) throws Exception {
        String key = _key.split("\\{")[0];
        String[] valuesToCheck = StringUtils.substringBetween(_key, "{", "}").split(";");
        JSONArray tempArr;
        int availableObjs;

        if(isJsonArray(Obj)) {
            tempArr = (JSONArray) Obj;
            availableObjs = ((JSONArray) Obj).length();
        } else {
            tempArr = ((JSONObject) Obj).getJSONArray(key);
            availableObjs = ((JSONObject) Obj).getJSONArray(key).length();
        }

        boolean objectPresent = true;
        Object tempObj = null;

        for(int i = 0; i < availableObjs; i++) {
            tempObj = tempArr.get(i);

            //Todo: Inprogress: Processing only JSONObjects - need to add capability to idenitify JSONArray
            if(isJsonArray(tempObj)) {
                tempObj = ((JSONObject) tempObj).get(key);
            } else {
                tempObj = ((JSONObject) tempObj).get(key);
            }

            for (String val: valuesToCheck) {
                String[] keyVal = val.split(":");
                //TODO 3: All values are converted to string and compared. This needs to be validated based on data types
                if(!((JSONObject) tempObj).opt(keyVal[0]).toString().equals(keyVal[1])){ objectPresent = false; };
            }
            if(objectPresent) { return tempObj; }
        }

        if(tempObj == null) { throw new Exception("Unable to find given values in json array"); }

        return tempObj;

    }

}
