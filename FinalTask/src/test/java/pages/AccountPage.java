package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage extends BasePage {
    @FindBy(xpath = "//a[@class='account']")
    private static WebElement accountLink;

    @FindBy(xpath = "//a[@title='My wishlists']")
    private static WebElement wishlistLink;

    public AccountPage() {
        PageFactory.initElements(super.driver, this);
    }

    public boolean checkLoggedUser(String firstName, String lastName) {
        return new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(accountLink)).getText().equals(String.format("%s %s", firstName, lastName));
    }

    public WishlistPage goWishlistPage() {
        wishlistLink.click();

        return new WishlistPage();
    }
}
