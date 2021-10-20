import drivers.WebDriverManager;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Optional;

public class AttachmentExtension implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable throwable) {
        WebDriver driver = WebDriverManager.getInstance().getDriver();
        addScreenshot(driver);
        addBrowserInfo(driver);
        WebDriverManager.getInstance().delDriver();
    }



    private void addBrowserInfo(WebDriver driver) {
        Allure.addAttachment("browser", ((RemoteWebDriver) driver).getCapabilities().getBrowserName());
        Allure.addAttachment("version", ((RemoteWebDriver) driver).getCapabilities().getVersion());
    }

    private void addScreenshot(WebDriver driver) {
        Allure.getLifecycle().addAttachment(
                "Screenshot",
                "image/png", "png",
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)
        );
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        WebDriverManager.getInstance().delDriver();
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        WebDriverManager.getInstance().delDriver();
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        WebDriverManager.getInstance().delDriver();
    }
}

