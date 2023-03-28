import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotMaker {
    WebDriver driver;

    public ScreenshotMaker(WebDriver webDriver) {
        driver = webDriver;
    }

    public void takeScreenshot(String name) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        File src = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File(String.format("images/%s_%s.png", name, ((RemoteWebDriver) driver).getCapabilities().getBrowserName())));
    }
}
