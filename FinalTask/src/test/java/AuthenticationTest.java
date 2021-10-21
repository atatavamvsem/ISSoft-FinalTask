import drivers.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pages.AccountPage;
import pages.CreateAccountPage;
import pages.LoginPage;
import utils.GenerateRandomUtil;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import utils.User;

import static utils.GenerateRandomUtil.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Feature("Authentication Test")
@ExtendWith(AttachmentExtension.class)
public class AuthenticationTest {
    private LoginPage loginPage;
    private CreateAccountPage createAccountPage;
    private AccountPage accountPage;

    private static User user;

    @BeforeAll
    public static void createUser() {
        user = new User(String.format("%s@%s.com", generateRandomString(5), generateRandomString(5)), generateRandomString(5), generateRandomString(5), generateRandomString(5));
    }

    @Test
    @DisplayName("Creating account test")
    @Order(1)
    public void createAccountTest() {
        loginPage = new LoginPage();

        createAccountPage = loginPage.createAccount(user.getEmail());

        accountPage = createAccountPage.fillAccountInfo(user.getPassword(), user.getFirstName(), user.getLastName());

        Assertions.assertTrue(accountPage.checkLoggedUser(user.getFirstName(), user.getLastName()), "Creating account is failed");
    }

    @Test
    @DisplayName("Login to account test")
    @Order(2)
    public void loginTest() {
        loginPage = new LoginPage();

        accountPage = loginPage.loginUser(user.getEmail(), user.getPassword());

        Assertions.assertTrue(accountPage.checkLoggedUser(user.getFirstName(), user.getLastName()), "Login is failed");
    }

    @AfterEach
    public void logout() {
        accountPage.logout();
    }

    @AfterAll
    public static void closeUp() {
        WebDriverManager.getInstance().delDriver();
    }
}
