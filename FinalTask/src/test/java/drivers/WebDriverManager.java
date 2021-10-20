package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ResourceProperties;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class WebDriverManager {
    private static WebDriverManager instance;
    private static WebDriver driver;

    private WebDriverManager() {

    }

    public static WebDriverManager getInstance() {
        if (instance == null) {
            instance = new WebDriverManager();
        }
        return instance;
    }

    public static WebDriver getDriver() {
        if (Objects.isNull(driver)) {
            switch (ResourceProperties.getDataProperty("run")) {
                case "local":
                    driver = runLocal();
                    break;
                case "docker":
                    driver = runDocker();
                    break;
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    private static WebDriver runLocal() {
        return new ChromeDriver();
    }

    private static WebDriver runDocker() {
        try {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            driver = new RemoteWebDriver(new URL("http://localhost:4445/wd/hub"), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    private static ChromeDriver createChromeDriver() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", getDownloadDirectory());
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        return new ChromeDriver(options);
    }

    private static FirefoxDriver createFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.download.dir", getDownloadDirectory());
        options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, text/csv");
        options.addPreference("pdfjs.disabled", true);

        return new FirefoxDriver(options);
    }

    public static String getDownloadDirectory() {
        return String.format("%s%sdownloadFiles", System.getProperty("user.dir"), File.separator);
    }

    public static void delDriver() {
        driver.quit();
        driver = null;
    }
}
