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
                        String.format("Incorrect page was opened. Expected title: %s. Actual title is: %s",
                                                                                EXPECTED_NAME, driver.getTitle())),
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
                        String.format("Incorrect page was opened. The opened page does not contain text 'You are logged out of:'" +
                                                                            " and has the title: %s", driver.getTitle())),
                () -> assertEquals(username, chooseAccountPage.getAccountName(),
                        "Incorrect user account name is shown")
        );
    }
}
