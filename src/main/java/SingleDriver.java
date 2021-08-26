import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SingleDriver {
    private static SingleDriver singleDriver = null;
    private final WebDriver driver;

    private SingleDriver() throws MalformedURLException {


       //MutableCapabilities sauceOptions = new MutableCapabilities();

        SafariOptions browserOptions = new SafariOptions();
        browserOptions.setCapability("platformName", "macOS 10.15");
        browserOptions.setCapability("browserVersion", "latest");
        //browserOptions.setCapability("sauce:options", sauceOptions);



        driver = new RemoteWebDriver(new URL("https://Igor111:f2c21765-5784-4b80-85ce-885ce9ac525d@ondemand.eu-central-1.saucelabs.com:443/wd/hub"), browserOptions);

       // Windows 10, Microsoft Edge (latest version) +

       // Windows 8.1, Mozilla Firefox 39.0

        //Linux, Google Chrome 40

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public static SingleDriver getSingleDriverInstance() throws MalformedURLException {
        if (singleDriver == null) {
            synchronized (SingleDriver.class) {
                if (singleDriver == null) {
                    singleDriver = new SingleDriver();
                }
            }
        }
        return singleDriver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void closeDriver() {
        if (singleDriver != null) {
            driver.close();
            singleDriver = null;
        }
    }



}
