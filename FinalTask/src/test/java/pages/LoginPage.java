package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//input[@id='email_create']")
    private static WebElement emailCreateInput;

    @FindBy(xpath = "//input[@id='email']")
    private static WebElement emailLoginInput;

    @FindBy(xpath = "//input[@id='passwd']")
    private static WebElement passwordInput;

    @FindBy(xpath = "//button[@id='SubmitCreate']")
    private static WebElement createAccountButton;

    @FindBy(xpath = "//button[@id='SubmitLogin']")
    private static WebElement loginButton;

    public LoginPage() {
        if (!"Login - My Store".equalsIgnoreCase(super.driver.getTitle())) {
            super.driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        }
        PageFactory.initElements(super.driver, this);
    }

    public CreateAccountPage createAccount(String email) {
        emailCreateInput.sendKeys(email);
        createAccountButton.click();

        return new CreateAccountPage();
    }

    public AccountPage loginUser(String email, String password) {
        emailLoginInput.sendKeys(email);
        passwordInput.sendKeys(password);
        loginButton.click();

        return new AccountPage();
    }
}
