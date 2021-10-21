package drivers;

import org.openqa.selenium.WebDriver;

public interface RunnerDriver {
    WebDriver getDriver(String browser);
}
