package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.math.BigDecimal;

public class CartPage extends BasePage{
    @FindBy(xpath = "//td[@id='total_product']")
    private static WebElement totalPrice;

    public CartPage() {
        PageFactory.initElements(driver, this);
    }

    public BigDecimal getTotalPrice() {
        return BigDecimal.valueOf(Double.parseDouble(totalPrice.getText().replaceAll("[\\$]", "")));
    }
}
