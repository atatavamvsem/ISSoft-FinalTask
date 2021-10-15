package pages;

import drivers.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;

    public BasePage() {
        this.driver = WebDriverManager.getInstance().getDriver();
    }

    protected boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    protected String getTextElement(WebElement element) {
        return new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(element)).getText();
    }
}