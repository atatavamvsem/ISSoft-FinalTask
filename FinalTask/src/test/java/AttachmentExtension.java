import drivers.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AttachmentExtension implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable throwable) {
        WebDriver driver = WebDriverManager.getInstance().getDriver();
        byte[] srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        saveScreenshot(srcFile);
        addInfo(driver);
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] saveScreenshot(byte[] screenshot) {
        return screenshot;
    }

    private void addInfo(WebDriver driver) {
        Allure.addAttachment("browser", ((RemoteWebDriver) driver).getCapabilities().getBrowserName());
        Allure.addAttachment("version", ((RemoteWebDriver) driver).getCapabilities().getVersion());
        Allure.addAttachment("platform", ((RemoteWebDriver) driver).getCapabilities().getPlatform().toString());
    }
}

