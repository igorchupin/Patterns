import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private final WebDriver driver;
    private final String EXPECTED_NAME = "Inbox — Yandex.Mail";

    @FindBy(xpath = "//div[@class='legouser__menu-header']//span[@class='user-account__name']")
    WebElement userAccountName;

    @FindBy(className = "user-pic__image")
    WebElement userIcon;

    @FindBy(linkText = "Log out")
    WebElement signOutButton;


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage openUserMenu() {
        userIcon.click();
        return this;
    }

    public String getAccountName() {
        openUserMenu();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(userAccountName));
        return userAccountName.getText();
    }

    public ChooseAccountPage clickSignOut() {
        openUserMenu();
        signOutButton.click();
        return new ChooseAccountPage(driver);
    }

    public String getPageName() {
        return EXPECTED_NAME;
    }
}


