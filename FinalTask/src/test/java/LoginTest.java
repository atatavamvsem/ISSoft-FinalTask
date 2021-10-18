import drivers.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.AccountPage;
import pages.CreateAccountPage;
import pages.LoginPage;
import utils.JsonParser;
import utils.User;

import java.io.File;
import java.util.stream.Stream;

public class LoginTest {
    private LoginPage loginPage;
    private AccountPage accountPage;

    private static User[] users;
    private static JsonParser parser;

    private static Stream<User> usersProvider() {
        parser = new JsonParser();
        users = parser.readFromFile(new File("src/test/resources/users.json"));
        return Stream.of(users);
    }

    @ParameterizedTest
    @MethodSource("usersProvider")
    public void loginTest(User user) {
        loginPage = new LoginPage();

        accountPage = loginPage.loginUser(user.getEmail(), user.getPassword());

        Assertions.assertTrue(accountPage.checkLoggedUser(user.getFirstName(), user.getLastName()), "Creating account is failed");
    }

    @AfterAll
    public static void closeUp() {
        WebDriverManager.getInstance().delDriver();
    }
}

