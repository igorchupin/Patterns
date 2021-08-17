import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
    private final WebDriver driver;
    private static final String LOGIN_PAGE_URL = "https://mail.yandex.com/";

    @FindBy(xpath ="//div[@class='HeadBanner-ButtonsWrapper']//a[2]")
    WebElement enterButton;

    @FindBy(id = "passp-field-login")
    WebElement emailField;

    @FindBy(id = "passp:sign-in")
    WebElement loginButton;

    @FindBy(id = "passp-field-passwd")
    WebElement passwordField;


    public LoginPage() {
        driver = SingleDriver.getSingleDriverInstance().getDriver();
        this.driver.navigate().to(LOGIN_PAGE_URL);
    }

    public HomePage loginToMail(String username, String password) {
        enterButton.click();
        emailField.sendKeys(username);
        loginButton.click();
        passwordField.sendKeys(password);
        loginButton.click();
        return new HomePage();
    }
}
