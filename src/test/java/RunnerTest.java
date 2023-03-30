
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
        MainPage mainPage = new MainPage(webDriver, webDriverWait);
        PicturesPage picturesPage = new PicturesPage(webDriver, webDriverWait);
        mainPage.clickMorePictures();
        mainPage.clickPictureCategory("Вышитые картины");
        mainPage.clickPictureGenre("Городской пейзаж");
        mainPage.clickApplyBtn();
        Integer picturesNumber = picturesPage.checkNumberPictures("Трамвайный путь");
        Assert.assertEquals(picturesNumber, 1);
    }

    @Description("Check picture style")
    @Test(priority = 2)
    public void checkPictureStyle() {
        MainPage mainPage = new MainPage(webDriver, webDriverWait);
        PicturesPage picturesPage = new PicturesPage(webDriver, webDriverWait);
        mainPage.clickMorePictures();
        mainPage.clickPictureCategory("Вышитые картины");
        mainPage.clickPictureGenre("Городской пейзаж");
        mainPage.clickApplyBtn();
        picturesPage.clickPicture("Трамвайный путь");
        String pictureStyle = picturesPage.checkPictureStyle();
        Assert.assertEquals(pictureStyle, "Реализм");
    }

    @Description("Check definite item in favorites")
    @Test(priority = 3)
    public void checkDefiniteItemInFavorites() {
        MainPage mainPage = new MainPage(webDriver, webDriverWait);
        PicturesPage picturesPage = new PicturesPage(webDriver, webDriverWait);
        FavoritePage favoritePage = new FavoritePage(webDriver, webDriverWait);
        mainPage.clickMorePictures();
        mainPage.clickPictureCategory("Батик");
        picturesPage.addPictureToFavorite();
        picturesPage.moveToFavoritePage();
        String actualPictureName = favoritePage.getFirstPictureName();
        String expectedPictureName =  picturesPage.getExpectedPictureName();
        Assert.assertEquals(expectedPictureName, actualPictureName);
    }

    @Description("Check picture by searching")
    @Test(priority = 4)
    public void checkSearchingPicture() {
        MainPage mainPage = new MainPage(webDriver, webDriverWait);
        PicturesPage picturesPage = new PicturesPage(webDriver, webDriverWait);
        mainPage.searchPicture("Жираф");
        String actualPictureName = picturesPage.actualFirstPictureName();
        Assert.assertTrue(actualPictureName.contains("Жираф"));
    }

    @Description("Check definite item in bin")
    @Test(priority = 5)
    @Parameters({ "name", "size" })
    public void checkDefiniteItemInBin(String name, Integer size) {
        MainPage mainPage = new MainPage(webDriver, webDriverWait);
        PicturesPage picturesPage = new PicturesPage(webDriver, webDriverWait);
        BinPage binPage = new BinPage(webDriver, webDriverWait);
        mainPage.clickMorePictures();
        mainPage.clickPictureCategory(name);
        picturesPage.addPictureToBin(size);
        picturesPage.moveToBinPage();
        Boolean isAllMatches = binPage.checkPicturesNamesPrices(picturesPage.getExpectedPictureNames(), picturesPage.getExpectedPicturePrices());
        Assert.assertTrue(isAllMatches);
    }
}
