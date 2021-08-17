import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private final WebDriver driver;

    @FindBy(xpath = "//div[@class='legouser__menu-header']//span[@class='user-account__name']")
    WebElement userAccountName;

    @FindBy(className = "user-pic__image")
    WebElement userIcon;

    @FindBy(linkText = "Log out")
    WebElement signOutButton;


    public HomePage() {
        driver = SingleDriver.getSingleDriverInstance().getDriver();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.titleContains("Inbox — Yandex.Mail"));
    }

    public String getAccountName() {
        userIcon.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(userAccountName));
        return userAccountName.getText();
    }

    public ChooseAccountPage signOut() {
        userIcon.click();
        signOutButton.click();
        return new ChooseAccountPage();
    }
}


