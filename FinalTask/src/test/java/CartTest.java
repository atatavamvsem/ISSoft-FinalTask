import drivers.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.*;
import utils.GenerateRandomUtil;
import utils.JsonParser;
import utils.User;

import java.io.File;
import java.math.BigDecimal;
import java.util.stream.Stream;

public class CartTest {
    private LoginPage loginPage;
    private AccountPage accountPage;
    private WishlistPage wishlistPage;
    private ProductPage productPage;
    private WomenClothesPage womenClothesPage;

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
        BigDecimal totalPrice = womenClothesPage.addClothesToCart();
        CartPage cartPage = womenClothesPage.getCartPage();
        Assertions.assertEquals(totalPrice, cartPage.getTotalPrice());
    }

    @AfterAll
    public static void closeUp() {
        WebDriverManager.getInstance().delDriver();
    }
}
