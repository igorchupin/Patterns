import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
    private final WebDriver driver;
    private final String loginPageURL = "https://mail.yandex.com/";

    @FindBy(xpath = "//*[@id=\"index-page-container\"]/div/div[2]/div/div/div[4]/a[2]")
    WebElement enterButton;

    @FindBy(id = "passp-field-login")
    WebElement emailField;

    @FindBy(id = "passp:sign-in")
    WebElement loginButton;

    @FindBy(id = "passp-field-passwd")
    WebElement passwordField;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.driver.navigate().to(loginPageURL);

    }

    public LoginPage sendUsername(String username) {
        emailField.sendKeys(username);
        return this;
    }

    public LoginPage sendPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public HomePage loginToMail(String username, String password) {
        enterButton.click();
        sendUsername(username);
        loginButton.click();
        sendPassword(password);
        loginButton.click();
        return new HomePage(driver);
    }
}
