
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

@Listeners({ TestAllureListeners.class })
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

    @AfterMethod()
    public void quit() {
        webDriver.quit();
    }

    @Description("Check picture name")
    @Test(priority = 1)
    public void checkPictureName() {
        EmbroideredPictures embroideredPictures = new EmbroideredPictures(webDriver, webDriverWait);
        embroideredPictures.checkPictureName("Трамвайный путь");
    }

    @Description("Check picture style")
    @Test(priority = 2)
    public void checkPictureStyle() {
        EmbroideredPictures embroideredPictures = new EmbroideredPictures(webDriver, webDriverWait);
        embroideredPictures.checkPictureStyle("Реализм", "Трамвайный путь");
    }

    @Description("Check definite item in favorites")
    @Test(priority = 3)
    public void checkDefiniteItemInFavorites() {
        BatikPictures batikPictures = new BatikPictures(webDriver);
        batikPictures.moveToBatikPictures();
        batikPictures.checkNumberOfItemsInFavorites();
        batikPictures.checkSavingToFavorites();
    }

    @Description("Check picture by searching")
    @Test(priority = 4)
    public void checkSearchingPicture() {
            SearchPicture searchPicture = new SearchPicture(webDriver, webDriverWait);
            searchPicture.checkSearchingPicture("Жираф");
    }

    @Description("Check definite item in bin")
    @Test(priority = 5)
    @Parameters("name")
    public void checkDefiniteItemInBin(String name) {
        Bin bin = new Bin(webDriver);
        bin.moveToDefinitePictures(name);
        bin.checkNumberOfItemsInBin();
        bin.checkSavingToBin();
    }
}
