import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class LoginLogoutTest {
    private static WebDriver driver;
    private LoginPage loginPage = new LoginPage(driver);
    private HomePage homePage = new HomePage(driver);
    private ChooseAccountPage chooseAccountPage = new ChooseAccountPage(driver);
    private final String username = "firstnamelastname1989";
    private final String password = "pTKJctHpbaj@t7M";

    @BeforeAll
    public static void beforeClass() {
        driver = SingleDriver.getInstance();
    }

    @AfterEach
    public void afterTest() {
      SingleDriver.clearBrowserData();
    }

    @AfterAll
    public static void closeDriver() {
        SingleDriver.close();
    }

    @Test
    @Tag("yandex_login_test")
    @DisplayName("Login in mail test")
    void yandexLoginTest() {
        loginPage.loginToMail(username, password);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.titleContains(homePage.getPageName()));

        assertAll("HomePage",
                () -> assertTrue(driver.getTitle().contains(homePage.getPageName()),
                        "Incorrect page was opened (Incorrect Title)"),
                () -> assertEquals(username + "\n" + username + "@yandex.com", homePage.getAccountName(),
                        "Incorrect user account name is shown")
        );
    }

    @Test
    @Tag("yandex_logout_test")
    @DisplayName("Logout from mail test")
    void yandexLogoutTest() {
        loginPage.loginToMail(username, password);
        homePage.clickSignOut();

        assertAll("Choose account Page",
                () -> assertTrue(chooseAccountPage.getLogoutText().matches("You are logged out of:"),
                        "Incorrect page was opened (Incorrect Title)"),
                () -> assertEquals(username, chooseAccountPage.getAccountName(),
                        "Incorrect user account name is shown")
        );
    }
}
