import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private final WebDriver driver;
    private final String EXPECTED_NAME = "Inbox — Yandex.Mail";
    private final By userAccountName = By.xpath("//div[@class='legouser__menu-header']//span[@class='user-account__name']");
    private final By userIcon = By.className("user-pic__image");
    private final By signOutButton = By.linkText("Log out");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage openUserMenu() {
        driver.findElement(userIcon).click();
        return this;
    }

    public String getAccountName() {
        openUserMenu();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(userAccountName));
        return driver.findElement(userAccountName).getText();
    }

    public ChooseAccountPage clickSignOut() {
        openUserMenu();
        driver.findElement(signOutButton).click();
        return new ChooseAccountPage(driver);
    }
    public String getPageName() {
        return EXPECTED_NAME;
    }
}


