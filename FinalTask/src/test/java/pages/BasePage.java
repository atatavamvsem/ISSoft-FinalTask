package pages;

import drivers.WebDriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;

    @FindBy(xpath = "//div[@id='block_top_menu']//a[@title='Women']")
    private static WebElement womenMenu;

    public BasePage() {
        this.driver = WebDriverManager.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public WomenClothesPage getWomenClothesPage(){
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(womenMenu)).click();
        return new WomenClothesPage();
    }

    protected String getTextElement(WebElement element) {
        return new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(element)).getText();
    }
}