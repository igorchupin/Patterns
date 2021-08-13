import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SingleDriver {
    private static WebDriver driver;

    public static WebDriver getInstance() {
        if (driver == null) {
            synchronized (SingleDriver.class) {
                if (driver == null) {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                }
            }
        }
        return driver;
    }

    public static void close() {
        if (driver != null) {
            driver.close();
        }
    }

    public static void clearBrowserData() {
        driver.get("chrome://settings/clearBrowserData");
        WebElement web = driver.findElement(By.xpath("//settings-ui"));
        web.sendKeys(Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.TAB, Keys.ENTER); // =))))))
        //I tried to delete cookies, to find other solutions in Internet but I did not found it =(((
    }
}
