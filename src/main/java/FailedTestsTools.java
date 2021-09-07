import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FailedTestsTools {
    private static WebDriver driver;
    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    String pathToFile = "src/main/resources/";

    public void getScreenAsFile() throws IOException {
        driver = SingleDriver.getSingleDriverInstance().getDriver();
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(pathToFile + timeStamp + "_" + driver.getTitle() + ".jpg"));
    }

    @Attachment
    public static byte[] attachScreenshot() throws IOException {
        driver = SingleDriver.getSingleDriverInstance().getDriver();
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        return screenshot.getScreenshotAs(OutputType.BYTES);
    }

    @Attachment
    public static String attachBrowserVersion() {
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName();
        String browserVersion = cap.getVersion();
        String platformName = cap.getPlatform().name();
        return "Browser name is: " + browserName + ", Browser version is: " + browserVersion + ", Platform " + platformName;
    }
}

