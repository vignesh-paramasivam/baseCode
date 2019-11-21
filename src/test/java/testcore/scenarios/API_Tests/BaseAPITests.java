package testcore.scenarios.API_Tests;

import org.testng.annotations.Test;
import testcore.scenarios.SupportTest;

public class BaseAPITests extends SupportTest {

    @Test(enabled = true, description = "This is a sample test for api")
    public void SampleTestForApi() throws Exception {
        application.createInstance()
                .login();
    }
}
