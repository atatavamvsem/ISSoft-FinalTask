package drivers;

import org.openqa.selenium.WebDriver;
import utils.ResourceProperties;

import java.util.Objects;

public class WebDriverManager {
    private static WebDriverManager instance;
    private static WebDriver driver;
    private static RunnerDriver runnerDriver;

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
                    runnerDriver = new RunLocal();
                    break;
                case "docker":
                    runnerDriver = new RunDocker();
                    break;
                case "grid":
                    runnerDriver = new RunGrid();
                    break;
                case "labs":
                    runnerDriver = new RunLabs();
                    break;
            }
            driver = runnerDriver.getDriver(ResourceProperties.getDataProperty("typeBrowser"));
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void delDriver() {
        driver.quit();
        driver = null;
    }
}
