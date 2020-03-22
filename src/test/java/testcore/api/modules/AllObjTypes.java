package testcore.api.modules;

import agent.IAgent;
import central.Configuration;
import io.restassured.response.Response;
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
        builder.add("address{postalCode:10021}", "City", "New York");


        //Output of json up to this level - created using the builder pattern,z
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


        return thisClass();
    }




}
