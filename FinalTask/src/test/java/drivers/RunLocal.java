package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class RunLocal implements RunnerDriver {

    @Override
    public WebDriver getDriver(String browser) {
        WebDriver driver;
        switch (browser) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                driver = new ChromeDriver();
        }
        return driver;
    }
}
