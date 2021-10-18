package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.GenerateRandomUtil;

public class WishlistPage extends BasePage{

    @FindBy(xpath = "//div[@id='block-history']")
    private static WebElement wishListBlock;

    @FindBy(xpath = "//div[@class='wlp_bought']//p[@id='s_title']")
    private static WebElement wishProductName;

    @FindBy(xpath = "//div[@id='best-sellers_block_right']//li[1]/a")
    private static WebElement firstTopProduct;

    @FindBy(xpath = "//input[@id='name']")
    private static WebElement wishlistNameInput;

    @FindBy(xpath = "//button[@id='submitWishlist']")
    private static WebElement submitWishlistButton;

    public WishlistPage() {
        if (!"My Store".equalsIgnoreCase(driver.getTitle())) {
            driver.get("http://automationpractice.com/index.php?fc=module&module=blockwishlist&controller=mywishlist");
        }
        PageFactory.initElements(driver, this);
    }

    public boolean wishlistIsPresent(String wishlistName) {
        return isElementDisplayed(driver.findElement(By.xpath(String.format("//a[contains(text(),'%s')]", wishlistName))));
    }

    public ProductPage goProductPage() {
        firstTopProduct.click();
        return new ProductPage();
    }

    public boolean productIsPresentInWishlist(String wishlistName, String productName) {
        driver.findElement(By.xpath(String.format("//a[contains(text(),'%s')]", wishlistName))).click();
        return new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(wishProductName)).getText().contains(productName);
    }

    public String createWishlist() {
        String name = GenerateRandomUtil.generateRandomString(5);
        wishlistNameInput.sendKeys(name);
        submitWishlistButton.click();

        return name;
    }

    public boolean wishlistBlockIsPresent() {
        return isElementDisplayed(wishListBlock);
    }
}
