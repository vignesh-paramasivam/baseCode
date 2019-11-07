package testcore.scenarios.Sample;

import org.testng.annotations.Test;
import testcore.scenarios.SupportTest;

public class Sample extends SupportTest {

    @Test(enabled = true, description = "This is a sample test")
    public void SampleTest() throws Exception {
        application.createInstance()
                .login()
                .navigateTo("menuName");
    }
}
