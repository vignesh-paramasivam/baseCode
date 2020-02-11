package testcore.scenarios.APITests;

import org.testng.annotations.Test;
import testcore.scenarios.SupportTest;

public class CreateComplaint extends SupportTest {

    @Test(enabled = true, description = "Validate complaint creation by citizen")
    public void createComplaint() throws Exception {
        apiInitialize()
                .generateAuthToken();
        //TODO: This is inprogress
    }
}
