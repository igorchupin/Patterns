import java.io.IOException;
import java.net.MalformedURLException;
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
        try {
            SingleDriver.getSingleDriverInstance().closeDriver();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void testDisabled(ExtensionContext extensionContext, Optional<String> optional) {
        try {
            SingleDriver.getSingleDriverInstance().closeDriver();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
        try {
            FailedTestsTools.attachScreenshot();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FailedTestsTools.attachBrowserVersion();
        try {
            SingleDriver.getSingleDriverInstance().closeDriver();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {
        try {
            SingleDriver.getSingleDriverInstance().closeDriver();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
