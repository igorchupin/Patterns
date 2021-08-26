import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.util.List;

public class ChooseAccountPage {

    private final WebDriver driver;
    private static final By YOU_ARE_LOGGED_OUT_TEXT = By.xpath("//h3[@class='AuthAccountList-unathorizedAccountsTitle']");
    private static final By CURRENT_ACCOUNT_NAME = By.xpath("//span[@class='CurrentAccount-displayName']");

    public ChooseAccountPage() throws MalformedURLException {
       driver = SingleDriver.getSingleDriverInstance().getDriver();
    }

    public String getTextThatUserWasLoggedOut() {
        WebElement account = driver.findElement(CURRENT_ACCOUNT_NAME);
        account.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(YOU_ARE_LOGGED_OUT_TEXT));
        return driver.findElement(YOU_ARE_LOGGED_OUT_TEXT).getText();
    }

    public String getAccountName() {
        return driver.findElement(CURRENT_ACCOUNT_NAME).getText();
    }
}
