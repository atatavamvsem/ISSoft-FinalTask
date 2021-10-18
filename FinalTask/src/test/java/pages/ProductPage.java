package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage{
    @FindBy(xpath = "//a[@id='wishlist_button']")
    private static WebElement addWishlistButton;

    @FindBy(xpath = "//div[contains(@class,'pb-center-column')]//h1")
    private static WebElement productName;

    @FindBy(xpath = "//a[@class='fancybox-item fancybox-close']")
    private static WebElement close;

    public ProductPage() {
        PageFactory.initElements(driver, this);
    }

    public String addToWishlist() {
        addWishlistButton.click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(close)).click();
        return productName.getText();
    }
}
