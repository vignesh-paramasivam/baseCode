package testcore.api.modules;

import agent.IAgent;
import central.Configuration;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import testcore.api.BaseApi;
import testcore.api.Builders.AllObjTypesBuilder;
import java.util.Map;

public class AllObjTypes extends BaseApi {

    /*
      THIS CLASS HAS DETAILS ABOUT ACCESSING BUILDER CLASS, ADDING NEW OBJECTS, UPDATING EXISTING OBJECTS, UPDATING
      OBJECTS BASED ON GIVEN JSON PATH CONDITION
    */

    public AllObjTypes(Configuration config, IAgent agent, Map<String, String> testData) throws Exception {
        super(config, agent, testData);
    }

    @Override
    public String pageName() {
        return BaseApi.class.getSimpleName();
    }

    private AllObjTypes thisClass() throws Exception {
        return new AllObjTypes(getConfig(), getAgent(), getTestData());
    }

    public AllObjTypes testAddDiffTypes() throws Exception {

        AllObjTypesBuilder builder = new AllObjTypesBuilder();

        //After initializing, json is built using builder pattern

        /*
        *
         {
            "firstName": "John",
            "address": {
                "state": "NY",
                "postalCode": 12121
            },
            "phoneNumbers": [{
                "type": "home",
                "number": "123-111"
            }, {
                "type": "fax",
                "number": "123-222"
            }]
        }
        * */

        builder.add("lastName", "Smith");
        builder.add("age", 25);

        //Json value after adding lastName and age,
        /*
        * {
            "firstName": "John",
            "address": {
                "state": "NY",
                "postalCode": 12121
            },
            "phoneNumbers": [{
                "type": "home",
                "number": "123-111"
            }, {
                "type": "fax",
                "number": "123-222"
            }],
            "lastName": "Smith",
            "age": 25
        }
                *
        * */

        builder.add("business", new JSONArray()
                .put(new JSONObject()
                        .put("type", "vehicle")
                        .put("car", new JSONObject()
                                .put("brand", "Toyota")
                                .put("model", new JSONArray().put("Prius").put("Camray"))
                                .put("quantity", 2))
                )
                .put(new JSONObject()
                        .put("type", "vehicle")
                        .put("bike", new JSONObject()
                                .put("brand", "Yamaha")
                                .put("model", new JSONArray().put(new JSONObject().put("new", "FZ").put("old", "FZS")))
                                .put("quantity", 5))
                ));


        builder.add("buyers", new JSONArray()
                .put(new JSONObject().put("online", new JSONArray())
                        .put("offline", new JSONArray()
                                .put("cash")
                                .put("card"))
                ));

        //Json value after adding business and buyers
        /*
                    * {
                "firstName": "John",
                "address": {
                    "state": "NY",
                    "postalCode": 12121
                },
                "phoneNumbers": [{
                    "type": "home",
                    "number": "123-111"
                }, {
                    "type": "fax",
                    "number": "123-222"
                }],
                "lastName": "Smith",
                "age": 25,
                "business": [{
                    "type": "vehicle",
                    "car": {
                        "brand": "Toyota",
                        "model": ["Prius", "Camray"],
                        "quantity": 2
                    }
                }, {
                    "type": "vehicle",
                    "bike": {
                        "brand": "Yamaha",
                        "model": [{
                            "new": "FZ",
                            "old": "FZS"
                        }],
                        "quantity": 5
                    }
                }],
                "buyers": [{
                    "online": [],
                    "offline": ["cash", "card"]
                }]
            }*/

        builder.updateViaJsonPath("$.phoneNumbers[?(@.type=='home')].type", "updateCheck");
        builder.addViaJsonPath("$.phoneNumbers[?(@.type=='fax')]", "newKey", "newVal");

        /* Json value after updating and adding values using JsonPath
        * {
                "firstName": "John",
                "address": {
                    "state": "NY",
                    "postalCode": 12121
                },
                "phoneNumbers": [{
                    "type": "updateCheck",
                    "number": "123-111"
                }, {
                    "type": "fax",
                    "number": "123-222",
                    "newKey": "newVal"
                }],
                "lastName": "Smith",
                "age": 25,
                "business": [{
                    "type": "vehicle",
                    "car": {
                        "brand": "Toyota",
                        "model": ["Prius", "Camray"],
                        "quantity": 2
                    }
                }, {
                    "type": "vehicle",
                    "bike": {
                        "brand": "Yamaha",
                        "model": [{
                            "new": "FZ",
                            "old": "FZS"
                        }],
                        "quantity": 5
                    }
                }],
                "buyers": [{
                    "online": [],
                    "offline": ["cash", "card"]
                }]
            }
        * */


        builder.readRequiredObjectValues(builder.build().toString(), "$.phoneNumbers[?(@.type=='fax')].newKey");

        return thisClass();
    }


}
