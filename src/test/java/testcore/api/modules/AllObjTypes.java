package testcore.api.modules;

import agent.IAgent;
import central.Configuration;
import io.restassured.response.Response;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.testng.Assert;
import testcore.api.BaseApi;
import testcore.api.Builders.AllObjTypesBuilder;
import testcore.api.Builders.CitizenAuthTokenBuilder;
import testcore.api.Builders.CitizenComplaintCreationBuilder;
import utils.APIUtils;

import java.util.HashMap;
import java.util.Map;

public class AllObjTypes extends BaseApi {

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

        builder.add("lastName", "Smith");
        builder.add("age", 25);

        //Output of json up to this level - created using the builder pattern,
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

        //Output of json up at this level
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

        builder.add("address[{postalCode:10021}]", "City", "New York");

        return thisClass();
    }


}
