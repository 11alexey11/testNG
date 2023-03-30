import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class TestAllureListeners implements IInvokedMethodListener {

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        WebDriver driver = Driver.getDriver();
        if (testResult.getStatus() == ITestResult.FAILURE) {
            ScreenshotMaker screenshotMaker = new ScreenshotMaker(driver);
            screenshotMaker.getScreenshot();
        }
    }
}
