import drivers.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.AccountPage;
import pages.CreateAccountPage;
import pages.LoginPage;
import utils.GenerateRandomUtil;
import utils.JsonParser;
import utils.User;

public class AutomationPractice {
    private LoginPage loginPage;
    private CreateAccountPage createAccountPage;
    private AccountPage accountPage;

    private String email;
    private String password;
    private String firstName;
    private String lastName;

    private static User[] users;
    private static JsonParser parser;

    @Test
    public void loginTest() {
        loginPage = new LoginPage();

        email = String.format("%s@%s.com", GenerateRandomUtil.generateRandomString(5),GenerateRandomUtil.generateRandomString(5));
        password = GenerateRandomUtil.generateRandomString(5);
        firstName = GenerateRandomUtil.generateRandomString(5);
        lastName = GenerateRandomUtil.generateRandomString(5);


        createAccountPage = loginPage.createAccount(email);

        accountPage = createAccountPage.fillAccountInfo(password, firstName, lastName);

        Assertions.assertTrue(accountPage.checkLoggedUser(firstName, lastName), "Creating account is failed");
    }

    @AfterAll
    public static void closeUp() {
        WebDriverManager.getInstance().delDriver();
    }
}
