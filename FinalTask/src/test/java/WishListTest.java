import drivers.WebDriverManager;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.AccountPage;
import pages.LoginPage;
import pages.ProductPage;
import pages.WishlistPage;
import utils.JsonParser;
import utils.ResourceProperties;
import utils.User;

import java.io.File;
import java.util.stream.Stream;

@Feature("Wishlist Test")
@ExtendWith(AttachmentExtension.class)
public class WishListTest {
    private LoginPage loginPage;
    private AccountPage accountPage;
    private WishlistPage wishlistPage;
    private ProductPage productPage;

    private static User[] users;
    private static JsonParser parser;

    private static Stream<User> usersProvider() {
        parser = new JsonParser();
        users = parser.readFromFile(new File("src/test/resources/users.json"));
        return Stream.of(users);
    }

    @ParameterizedTest
    @MethodSource("usersProvider")
    public void autoWishlistTest(User user) {
        loginPage = new LoginPage();

        accountPage = loginPage.loginUser(user.getEmail(), user.getPassword());

        wishlistPage = accountPage.goWishlistPage();
        Assertions.assertFalse(wishlistPage.wishlistBlockIsPresent(), "Wishlist isn't empty");

        productPage = wishlistPage.goProductPage();
        String productName = productPage.addToWishlist();

        wishlistPage = new WishlistPage();
        Assertions.assertTrue(wishlistPage.productIsPresentInWishlist(ResourceProperties.getDataProperty("autoWishlistName"), productName), "Wrong product in wishlist");
    }

    @ParameterizedTest
    @MethodSource("usersProvider")
    public void createdWishlistTest(User user) {
        loginPage = new LoginPage();

        accountPage = loginPage.loginUser(user.getEmail(), user.getPassword());

        wishlistPage = accountPage.goWishlistPage();
        String wishlistName = wishlistPage.createWishlist();

        productPage = wishlistPage.goProductPage();
        String productName = productPage.addToWishlist();

        wishlistPage = new WishlistPage();
        Assertions.assertTrue(wishlistPage.productIsPresentInWishlist(wishlistName, productName), "Wrong product in wishlist");
    }

    @AfterEach
    public void deleteWishlist() {
        wishlistPage.deleteWishlist();
        wishlistPage.logout();
    }

    @AfterAll
    public static void closeUp() {
        WebDriverManager.getInstance().delDriver();
    }
}
