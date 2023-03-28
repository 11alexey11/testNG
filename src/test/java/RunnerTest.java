import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class RunnerTest extends Driver {
    protected Driver driver;
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    @BeforeMethod
    @Parameters("browser")
    public void initializeDriver(String browser) {
        driver = new Driver();
        webDriver = driver.initialize(browser);
        webDriverWait = Driver.getWebDriverWait();
    }

    @AfterMethod
    public void takeScreenshotFailTest(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                ScreenshotMaker screenshotMaker = new ScreenshotMaker(webDriver);
                screenshotMaker.takeScreenshot(result.getName());
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot");
            }
        }
    }

    @AfterMethod(dependsOnMethods = "takeScreenshotFailTest")
    public void quit() {
        webDriver.quit();
    }

    @Test
    public void checkPictureName() {
        EmbroideredPictures embroideredPictures = new EmbroideredPictures(webDriver, webDriverWait);
        embroideredPictures.checkPictureName("Трамвайный путь");
    }
    @Test
    public void checkPictureStyle() {
        EmbroideredPictures embroideredPictures = new EmbroideredPictures(webDriver, webDriverWait);
        embroideredPictures.checkPictureStyle("Реализм", "Трамвайный путь");
    }

    @Test
    public void checkDefiniteItemInFavorites() {
        BatikPictures batikPictures = new BatikPictures(webDriver);
        batikPictures.moveToBatikPictures();
        batikPictures.checkNumberOfItemsInFavorites();
        batikPictures.checkSavingToFavorites();
    }

    @Test
    public void checkSearchingPicture() {
        SearchPicture searchPicture = new SearchPicture(webDriver, webDriverWait);
        searchPicture.checkSearchingPicture("Жираф");
    }

    @Test
    @Parameters("name")
    public void checkDefiniteItemInBin(String name) {
        Bin bin = new Bin(webDriver);
        bin.moveToDefinitePictures(name);
        bin.checkNumberOfItemsInBin();
        bin.checkSavingToBin();
    }
}
