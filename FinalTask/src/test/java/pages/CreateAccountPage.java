package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.GenerateRandomUtil;
import utils.ResourceProperties;

import static utils.GenerateRandomUtil.generateRandomString;

public class CreateAccountPage extends BasePage {
    private int lengthString = Integer.parseInt(ResourceProperties.getDataProperty("lengthRandomString"));

    @FindBy(xpath = "//input[@id='customer_firstname']")
    private static WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='customer_lastname']")
    private static WebElement lastNameInput;

    @FindBy(xpath = "//input[@id='passwd']")
    private static WebElement passwordInput;

    @FindBy(xpath = "//input[@id='firstname']")
    private static WebElement addressFirstNameInput;

    @FindBy(xpath = "//input[@id='lastname']")
    private static WebElement addressLastNameInput;

    @FindBy(xpath = "//input[@id='address1']")
    private static WebElement addressInput;

    @FindBy(xpath = "//input[@id='city']")
    private static WebElement cityInput;

    @FindBy(xpath = "//select[@id='id_state']")
    private static WebElement stateSelect;

    @FindBy(xpath = "//select[@id='id_country']")
    private static WebElement countrySelect;

    @FindBy(xpath = "//input[@id='postcode']")
    private static WebElement postalCodeInput;

    @FindBy(xpath = "//input[@id='phone_mobile']")
    private static WebElement phoneMobileInput;

    @FindBy(xpath = "//button[@id='submitAccount']")
    private static WebElement submitAccountButton;

    public CreateAccountPage() {
        PageFactory.initElements(super.driver, this);
    }


    public AccountPage fillAccountInfo(String password, String firstName, String lastName) {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(firstNameInput)).sendKeys(firstName);

        lastNameInput.sendKeys(lastName);
        passwordInput.sendKeys(password);

        addressFirstNameInput.clear();
        addressFirstNameInput.sendKeys(firstName);
        addressLastNameInput.clear();
        addressLastNameInput.sendKeys(lastName);
        addressInput.sendKeys(generateRandomString(lengthString));
        cityInput.sendKeys(generateRandomString(lengthString));

        Select countrySlct = new Select(countrySelect);
        countrySlct.selectByValue(ResourceProperties.getDataProperty("countryValue"));

        Select stateSlct = new Select(stateSelect);
        stateSlct.selectByValue(String.valueOf(GenerateRandomUtil.generateRandomInt(stateSlct.getOptions().size())));

        postalCodeInput.sendKeys(GenerateRandomUtil.generateRandomStringOfInt(5));
        phoneMobileInput.sendKeys(GenerateRandomUtil.generateRandomStringOfInt(7));

        submitAccountButton.click();

        return new AccountPage();
    }
}
