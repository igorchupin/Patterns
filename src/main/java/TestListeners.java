import java.io.IOException;
import java.util.Optional;

import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TestListeners implements TestWatcher{
    FailedTestsTools make = new FailedTestsTools();

    @Override
    public void testAborted(ExtensionContext extensionContext, Throwable throwable) {
       SingleDriver.getSingleDriverInstance().closeDriver();
    }

    @Override
    public void testDisabled(ExtensionContext extensionContext, Optional<String> optional) {
        SingleDriver.getSingleDriverInstance().closeDriver();
    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
        try {
            FailedTestsTools.attachScreenshot();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FailedTestsTools.attachBrowserVersion();
        SingleDriver.getSingleDriverInstance().closeDriver();
    }

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {
        SingleDriver.getSingleDriverInstance().closeDriver();
    }
}
