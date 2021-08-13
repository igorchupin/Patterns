import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;
    private final String loginPageURL = "https://mail.yandex.com/";
    private final By enterButton = By.xpath("//*[@id=\"index-page-container\"]/div/div[2]/div/div/div[4]/a[2]");
    private final By emailField = By.id("passp-field-login");
    private final By loginButton = By.id("passp:sign-in");
    private final By passwordField = By.id("passp-field-passwd");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.driver.navigate().to(loginPageURL);
    }

    public LoginPage sendUsername(String username) {
        driver.findElement(emailField).sendKeys(username);
        return this;
    }

    public LoginPage sendPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public HomePage loginToMail(String username, String password) {
        driver.findElement(enterButton).click();
        sendUsername(username);
        driver.findElement(loginButton).click();
        sendPassword(password);
        driver.findElement(loginButton).click();
        return new HomePage(driver);
    }
}
