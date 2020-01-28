package testcore.scenarios.Slang;

import org.testng.annotations.Test;
import testcore.scenarios.SupportTest;

public class Slang extends SupportTest {

    @Test(enabled = true, description = "This is a sample test")
    public void SampleTest() throws Exception {
        application.createInstance()
                .login()
                .voiceInput();
    }
}
