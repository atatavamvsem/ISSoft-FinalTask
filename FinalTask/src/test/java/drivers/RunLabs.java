package drivers;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ResourceProperties;

import java.net.MalformedURLException;
import java.net.URL;

public class RunLabs implements RunnerDriver {
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
            MutableCapabilities sauceOptions = new MutableCapabilities();
            sauceOptions.setCapability("username", System.getenv(ResourceProperties.getCredProperty("username")));
            sauceOptions.setCapability("access_key", System.getenv(ResourceProperties.getCredProperty("accessKey")));
            sauceOptions.setCapability("name", "Automation practise tests");
            sauceOptions.setCapability("platform", "Windows 10");
            sauceOptions.setCapability("browserVersion", "latest");

            ChromeOptions options = new ChromeOptions();
            options.setCapability("sauce:options", sauceOptions);
            URL url = new URL(String.format("https://%s:%s@ondemand.eu-central-1.saucelabs.com:443/wd/hub", ResourceProperties.getCredProperty("username"), ResourceProperties.getCredProperty("accessKey")));

            driver = new RemoteWebDriver(url, options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    private WebDriver runFirefoxDriver() {
        try {
            MutableCapabilities sauceOptions = new MutableCapabilities();
            sauceOptions.setCapability("username", System.getenv(ResourceProperties.getCredProperty("username")));
            sauceOptions.setCapability("access_key", System.getenv(ResourceProperties.getCredProperty("accessKey")));
            sauceOptions.setCapability("name", "Automation practise tests");
            sauceOptions.setCapability("platform", "Windows 10");
            sauceOptions.setCapability("browserVersion", "latest");

            FirefoxOptions options = new FirefoxOptions();
            options.setCapability("sauce:options", sauceOptions);
            URL url = new URL(String.format("https://%s:%s@ondemand.eu-central-1.saucelabs.com:443/wd/hub", ResourceProperties.getCredProperty("username"), ResourceProperties.getCredProperty("accessKey")));

            driver = new RemoteWebDriver(url, options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }
}
