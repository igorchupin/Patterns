import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

public class LoginLogoutTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private ChooseAccountPage chooseAccountPage;
    private final String username = "firstnamelastname1989";
    private final String password = "pTKJctHpbaj@t7M";
    private final String EXPECTED_NAME = "Inbox — Yandex.Mail";

    @BeforeEach
    public void beforeTest() {
        driver = SingleDriver.getSingleDriverInstance().getDriver();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
    }

    @AfterEach
    public void afterTest() {
     SingleDriver.getSingleDriverInstance().closeDriver();
    }

    @Test
    @Tag("yandex_login_test")
    @DisplayName("Login in mail test")
    void yandexLoginTest() {
        loginPage.loginToMail(username, password);
        homePage = PageFactory.initElements(driver, HomePage.class);

        assertAll("HomePage",
                () -> assertTrue(driver.getTitle().contains(EXPECTED_NAME),
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
        homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.signOut();
        chooseAccountPage = PageFactory.initElements(driver, ChooseAccountPage.class);

        assertAll("Choose account Page",
                () -> assertTrue(chooseAccountPage.getTextThatUserWasLoggedOut().matches("You are logged out of:"),
                        "Incorrect page was opened (Incorrect Title)"),
                () -> assertEquals(username, chooseAccountPage.getAccountName(),
                        "Incorrect user account name is shown")
        );
    }
}
