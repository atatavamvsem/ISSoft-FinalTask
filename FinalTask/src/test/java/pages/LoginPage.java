package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{
    @FindBy(xpath = "//input[@id='email_create']")
    private static WebElement emailInput;

    @FindBy(xpath = "//button[@id='SubmitCreate']")
    private static WebElement createAccountButton;

    public LoginPage() {
        if (!"Login - My Store".equalsIgnoreCase(super.driver.getTitle())) {
            super.driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        }
        PageFactory.initElements(super.driver, this);
    }

    public CreateAccountPage createAccount(String email) {
        emailInput.sendKeys(email);
        createAccountButton.click();

        return new CreateAccountPage();
    }
}
