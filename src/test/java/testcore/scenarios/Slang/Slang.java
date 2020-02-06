package testcore.scenarios.Slang;

import org.testng.annotations.Test;
import testcore.scenarios.SupportTest;

public class Slang extends SupportTest {

    @Test(enabled = true, description = "Validate voice input in SlangLabs Web")
    public void SampleTest() throws Exception {
       /* application().createInstance()
                .login()
                .voiceInput();*/
    }

    @Test(enabled = true, description = "Test to validate speech api")
    public void ValidateSpeechApi() throws Exception {
        apiInitialize().validateSpeechText();

    }

    @Test(enabled = true, description = "Validate airtel app with English Voice")
    public void airtelAppEnglish() throws Exception {
        application()
        .validateVoiceNavigation();
    }

    @Test(enabled = true, description = "Validate airtel app with Hindi Voice")
    public void airtelAppHindi() throws Exception {
        application().validateVoiceNavigationHindi();
    }

}
