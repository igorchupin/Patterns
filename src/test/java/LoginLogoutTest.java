import org.junit.jupiter.api.*;
import org.openqa.selenium.*;

import static org.junit.jupiter.api.Assertions.*;

public class LoginLogoutTest {
    private  WebDriver driver;
    private LoginPage loginPage = new LoginPage();
    private final String username = "firstnamelastname1989";
    private final String password = "pTKJctHpbaj@t7M";
    private static final String EXPECTED_NAME = "Inbox — Yandex.Mail";

    @BeforeEach
    public void beforeTest () {
        driver = SingleDriver.getSingleDriverInstance().getDriver();
    }

    @AfterEach
    public void afterTest()  {
     SingleDriver.getSingleDriverInstance().closeDriver();
    }

    @Test
    @Tag("yandex_login_test")
    @DisplayName("Login in mail test")
    void yandexLoginTest() {
        HomePage homePage = loginPage.loginToMail(username, password);

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
       HomePage homePage = loginPage.loginToMail(username, password);
       ChooseAccountPage chooseAccountPage = homePage.signOut();

        assertAll("Choose account Page",
                () -> assertTrue(chooseAccountPage.getTextThatUserWasLoggedOut().matches("You are logged out of:"),
                        "Incorrect page was opened (Incorrect Title)"),
                () -> assertEquals(username, chooseAccountPage.getAccountName(),
                        "Incorrect user account name is shown")
        );
    }
}
