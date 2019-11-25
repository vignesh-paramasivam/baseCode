package testcore.scenarios.APITests;

import org.testng.annotations.Test;
import testcore.scenarios.SupportTest;
import utils.APIUtils;

public class BaseAPITests extends SupportTest {

    @Test(enabled = true, description = "This is a sample test for api")
    public void SampleTestForApi() throws Exception {
        APIUtils.get("http://yahoo.com");
    }
}
