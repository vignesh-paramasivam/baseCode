package testcore.scenarios.APITests;

import org.testng.annotations.Test;
import testcore.scenarios.SupportTest;

public class AllObjectTypes extends SupportTest {

    @Test(enabled = true, description = "Check if we can create or update objects at any depth")
    public void validateAllObjTypes() throws Exception {
        allObjTypes()
                .testAddDiffTypes();
    }
}
