package testcore.api.Builders;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.json.JSONTokener;


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
        actionInfo.put(new JSONObject().put("media", new JSONArray()));

        return actionInfo;
    }

    private JSONArray services() throws Exception {
        services = new JSONArray();

        services.put(new JSONObject()
                .put("serviceCode", "GarbageNeedsTobeCleared")
                .put("description", "test")
                .put("addressDetail", new JSONArray().put(new JSONObject()
                        .put("latitude", "31.322422")
                        .put("longitude", "75.573419")
                        .put("city", "pb.jalandhar")
                        .put("mohalla", "JLC216")
                        .put("houseNoAndStreetName", "test")
                        .put("landmark", "test"))
                        .put(new JSONObject()
                                        .put("latitude", "21.322422")
                                        .put("longitude", "21.573419")
                                        .put("city", "bangalore")
                                        .put("mohalla", "JLC216")
                                        .put("houseNoAndStreetName", "123")
                                        .put("landmark", "123"))
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

        JSONObject jObjInstance;
        JSONArray jArrInstance;

        for(String _key: keys) {
            // Check if object is JSON array
            String[] keyCheck = _key.split("\\{");
            //TODO 1: Accessing json arrays inside json array to be done
            if(((JSONObject) jObj).optJSONArray(keyCheck[0]) != null) {

                //if we need to get the object inside json array
                if(keyCheck.length > 1) {
                    jObj = getKeyInDepth(_key, jObj);
                } else {
                    jObj = ((JSONObject) jObj).getJSONArray(_key).get(0);
                }
            } else if (((JSONObject) jObj).optJSONObject(_key) != null) {
                jObjInstance = (JSONObject) jObj;
                jObj = jObjInstance.getJSONObject(_key);
            }
        }

        Object jSn = new JSONTokener(jObj.toString()).nextValue();

        if(jSn instanceof JSONArray) {
            //TODO 2: May get class cast exception - need to address
            jArrInstance = (JSONArray) jObj;
            jArrInstance.put(value);

        } else if (jSn instanceof JSONObject){
            jObjInstance = (JSONObject) jObj;
            jObjInstance.put(key, value);
        }
        return this;
    }

    private Object getKeyInDepth(String _key, Object Obj) throws Exception {
        String key = _key.split("\\{")[0];
        String[] valuesToCheck = StringUtils.substringBetween(_key, "{", "}").split(";");
        JSONArray tempArr = ((JSONObject) Obj).getJSONArray(key);
        int availableObjs = ((JSONObject) Obj).getJSONArray(key).length();

        boolean objectPresent = true;
        Object tempObj = null;

        for(int i = 0; i < availableObjs; i++) {
            tempObj = tempArr.get(i);

            for (String val: valuesToCheck) {
              String[] keyVal = val.split(":");
              //TODO 3: All values are converted to string and compared. This needs to be validated based on data types
                if(!((JSONObject) tempObj).opt(keyVal[0]).toString().equals(keyVal[1])){
                    objectPresent = false;
                };
            }
            if(objectPresent) { return tempObj; }
        }

        if(tempObj == null) { throw new Exception("Unable to find given values in json array"); }

        return tempObj;

    }

}
