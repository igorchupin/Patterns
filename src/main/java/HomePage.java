import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private final WebDriver driver;
    private static final By USER_ACCOUNT_NAME = By.xpath("//div[@class='legouser__menu-header']//span[@class='user-account__name']");
    private static final By USER_ICON = By.className("user-pic__image");
    private static final By SIGN_OUT_BUTTON = By.linkText("Log out");

   public HomePage() {
       driver= SingleDriver.getSingleDriverInstance().getDriver();
       WebDriverWait wait = new WebDriverWait(driver, 5);
       wait.until(ExpectedConditions.titleContains("Inbox — Yandex.Mail"));
    }

    public String getAccountName() {
        driver.findElement(USER_ICON).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(USER_ACCOUNT_NAME));
        return driver.findElement(USER_ACCOUNT_NAME).getText();
    }

    public ChooseAccountPage signOut() {
        driver.findElement(USER_ICON).click();
        driver.findElement(SIGN_OUT_BUTTON).click();
        return new ChooseAccountPage();
    }
}


