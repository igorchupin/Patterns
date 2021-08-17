import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SingleDriver {
    private static SingleDriver singleDriver = null;
    private WebDriver driver;

    private SingleDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public static SingleDriver getSingleDriverInstance () {
        if (singleDriver == null) {
            singleDriver = new SingleDriver();
        }
        return singleDriver;
    }

    public WebDriver getDriver () {
        return driver;
    }

    public void closeDriver () {
        if (singleDriver != null) {
            driver.close();
            singleDriver = null;
        }
    }
}