package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.ResourceProperties;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

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
        if (driver == null) {
            switch (ResourceProperties.getDataProperty("typeBrowser")) {
                case "firefox":
                    driver = createFirefoxDriver();
                    break;
                default:
                    driver = createChromeDriver();
            }
        }
        driver.manage().window().maximize();
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
