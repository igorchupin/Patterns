import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.TmsLink;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.TestResult;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.junit.platform.launcher.TestExecutionListener;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(TestListeners.class)
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


    @TmsLink(value = "1TT")
    @Feature("Login")
    @Description(value = "This test verifies login into Yandex")
    @Test
    @Tag("yandex_login_test")
    @DisplayName("Login in mail test")
    void yandexLoginTest() {
        HomePage homePage = loginPage.loginToMail(username, password);

         assertTrue(driver.getTitle().contains(EXPECTED_NAME),
                        String.format("Incorrect page was opened. Expected title: %s. Actual title is: %s",
                                EXPECTED_NAME, driver.getTitle()));
            assertEquals(username + "\n" + username + "@yandex.com", homePage.getAccountName(),
                        "Incorrect user account name is shown");

    }

    @TmsLink(value = "2TT")
    @Feature("Logout")
    @Description(value = "This test verifies logout from Yandex")
    @Test
    @Tag("yandex_logout_test")
    @DisplayName("Logout from mail test")
    void yandexLogoutTest() {
       HomePage homePage = loginPage.loginToMail(username, password);
       ChooseAccountPage chooseAccountPage = homePage.signOut();

             assertTrue(chooseAccountPage.getTextThatUserWasLoggedOut().matches("You are logged out of:"),
                        String.format("Incorrect page was opened. The opened page does not contain text 'You are logged out of:'" +
                                " and has the title: %s", driver.getTitle()));
                 assertEquals(username, chooseAccountPage.getAccountName(),
                        "Incorrect user account name is shown");
    }

    @TmsLink(value = "3TT")
    @Feature("Logout")
    @Description(value = "This test should always fail")
    @Test
    @Tag("yandex_logout_test")
    @DisplayName("Always failed test")
    void yandexLogoutFailTest() {
        HomePage homePage = loginPage.loginToMail(username, password);
        ChooseAccountPage chooseAccountPage = homePage.signOut();

        assertTrue(chooseAccountPage.getTextThatUserWasLoggedOut().matches("!!!You are logged out of:"),
                String.format("Incorrect page was opened. The opened page does not contain text 'You are logged out of:'" +
                        " and has the title: %s", driver.getTitle()));
        assertEquals(username, chooseAccountPage.getAccountName(),
                "Incorrect user account name is shown");
    }


    @TmsLink(value = "4TT")
    @Feature("Login")
    @Description(value = "This test will always fail with message 'Incorrect Page was opened'")
    @Test
    @Tag("yandex_login_test")
    @DisplayName("Login in mail test with error")
    void yandexLoginErrorMessageTest() {
        HomePage homePage = loginPage.loginToMail(username, password);

        assertTrue(driver.getTitle().contains(EXPECTED_NAME + "!!!"),
                String.format("Incorrect page was opened. Expected title: %s. Actual title is: %s",
                        EXPECTED_NAME, driver.getTitle()));
    }
}

