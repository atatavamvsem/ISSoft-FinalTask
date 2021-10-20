package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.GenerateRandomUtil;

import java.math.BigDecimal;
import java.util.List;

public class WomenClothesPage extends BasePage {

    @FindBy(xpath = "//a[@title='View my shopping cart']")
    private static WebElement cartButton;

    @FindBy(xpath = "//div[@class='product-container']")
    private static List<WebElement> allClothes;

    @FindBy(xpath = "//span[@title='Close window']")
    private static WebElement closeButton;

    public WomenClothesPage() {
        PageFactory.initElements(driver, this);
    }

    public BigDecimal addClothesToCart(int numberClothes) {
        BigDecimal totalPrice = new BigDecimal(0);
        int[] positionsClothes = GenerateRandomUtil.getArrayWithRandomInt(allClothes.size() - 1, numberClothes);

        for (int position : positionsClothes) {
            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(By.xpath(String.format("(//div[@class='product-container'])[%s]", position + 1))));
            actions.perform();

            new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(String.format("(//a[@title='Add to cart'])[%s]", position + 1))))).click();
            totalPrice = totalPrice.add(BigDecimal.valueOf(Double.parseDouble(driver.findElement(By.xpath(String.format("(//div[@class='product-image-container']//span[@itemprop='price'])[%s]", position + 1))).getText().replaceAll("[\\$]", ""))));
            new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(closeButton)).click();
        }
        return totalPrice;
    }

    public CartPage getCartPage() {
        cartButton.click();

        return new CartPage();
    }
}
