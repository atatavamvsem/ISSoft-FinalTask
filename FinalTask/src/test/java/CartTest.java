import drivers.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.*;
import utils.JsonParser;
import utils.ResourceProperties;
import utils.User;

import java.io.File;
import java.math.BigDecimal;
import java.util.stream.Stream;

public class CartTest {
    private LoginPage loginPage;
    private AccountPage accountPage;
    private WomenClothesPage womenClothesPage;
    private CartPage cartPage;

    private static User[] users;
    private static JsonParser parser;

    private static Stream<User> usersProvider() {
        parser = new JsonParser();
        users = parser.readFromFile(new File("src/test/resources/users.json"));
        return Stream.of(users);
    }

    @ParameterizedTest
    @MethodSource("usersProvider")
    public void loginTest1(User user) {
        loginPage = new LoginPage();

        accountPage = loginPage.loginUser(user.getEmail(), user.getPassword());

        womenClothesPage = accountPage.getWomenClothesPage();
        BigDecimal totalPrice = womenClothesPage.addClothesToCart(Integer.parseInt(ResourceProperties.getDataProperty("numberPositions")));

        cartPage = womenClothesPage.getCartPage();

        Assertions.assertEquals(totalPrice, cartPage.getTotalPrice());
    }

    @AfterEach
    public void cleanup(){
        cartPage.deleteCart();
        cartPage.logout();
    }

    @AfterAll
    public static void closeUp() {
        WebDriverManager.getInstance().delDriver();
    }
}
