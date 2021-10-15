package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage extends BasePage{
    @FindBy(xpath = "//a[@class='account']")
    private static WebElement accountLink;

    public AccountPage() {
        PageFactory.initElements(super.driver, this);
    }


    public boolean print(String firstName, String lastName) {
        return accountLink.getText().equals(String.format("%s %s", firstName, lastName));
    }
}
