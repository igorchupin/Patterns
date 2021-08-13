import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ChooseAccountPage {

    private final WebDriver driver;
    private final By youAreLoggedOutText = By.xpath("//h3[@class='AuthAccountList-unathorizedAccountsTitle']");
    private final By accountsList = By.xpath("//div/a[@class='AuthAccountListItem']");
    private final By currentAccountName = By.xpath("//span[@class='CurrentAccount-displayName']");

    public ChooseAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getLogoutText() {
        WebElement account = driver.findElement(currentAccountName);
        account.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(youAreLoggedOutText));
        return driver.findElement(youAreLoggedOutText).getText();
    }

    public List<WebElement> getAccountsList() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(youAreLoggedOutText));
        return driver.findElements(accountsList);
    }

    public String getAccountName() {
        return driver.findElement(currentAccountName).getText();
    }


}
