package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RunGrid implements RunnerDriver {
    private WebDriver driver;

    @Override
    public WebDriver getDriver(String browser) {
        switch (browser) {
            case "firefox":
                driver = runFirefoxDriver();
                break;
            default:
                driver = runChromeDriver();
        }
        return driver;
    }

    private WebDriver runChromeDriver() {
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    private WebDriver runFirefoxDriver() {
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.firefox());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }
}
