import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ChooseAccountPage {

    private final WebDriver driver;

    @FindBy(xpath = "//h3[@class='AuthAccountList-unathorizedAccountsTitle']")
    WebElement youAreLoggedOutText;

    @FindBy(xpath = "//span[@class='CurrentAccount-displayName']")
    WebElement currentAccountName;

    public ChooseAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getLogoutText() {
        currentAccountName.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(youAreLoggedOutText));
        return youAreLoggedOutText.getText();
    }

    public String getAccountName() {
        return currentAccountName.getText();
    }

}
