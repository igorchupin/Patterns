import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public class LoginPage {
    private final WebDriver driver ;
    private static final String LOGIN_PAGE_URL = "https://mail.yandex.com/";
    private static final By ENTER_BUTTON = By.xpath("//div[@class='HeadBanner-ButtonsWrapper']//a[2]");
    private static final By EMAIL_FIELD = By.id("passp-field-login");
    private static final By LOGIN_BUTTON = By.id("passp:sign-in");
    private static final By PASSWORD_FIELD = By.id("passp-field-passwd");

    public LoginPage() throws MalformedURLException {
       driver = SingleDriver.getSingleDriverInstance().getDriver();
       this.driver.navigate().to(LOGIN_PAGE_URL);
    }

        public HomePage loginToMail(String username, String password) throws MalformedURLException {
        driver.findElement(ENTER_BUTTON).click();
        driver.findElement(EMAIL_FIELD).sendKeys(username);;
        driver.findElement(LOGIN_BUTTON).click();
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new HomePage();

    }
}
