import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage {
    @FindBy(xpath = "//li[@class='menu-group gids']")
    WebElement morePicturesBtn;

    @FindBy(xpath = "//li[@class='menu-extra-item gids']//*[text()=' Вышитые картины']")
    WebElement embroideredPictureBtn;

    @FindBy(xpath = "//li[@class='menu-extra-item gids']//*[text()=' Батик']")
    WebElement batikPictureBtn;

    @FindBy(xpath = "//li[@class='menu-extra-item gids']//*[text()=' Ювелирное искусство']")
    WebElement jewelryArt;

    @FindBy(xpath = "//label[text()=' Городской пейзаж']")
    WebElement urbanLandscapeBtn;

    @FindBy(xpath = "//div[@id='applymsg']")
    WebElement applyBtn;

    @FindBy(xpath = "//input[@name='qs']")
    WebElement mainInputSearch;

    @FindBy(xpath = "//input[@class='inp']")
    WebElement secondaryInputSearch;

    public MainPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver, webDriverWait);
    }

    @Step(value = "Click on more pictures")
    public void clickMorePictures() {
        waitElementToBeClickable(morePicturesBtn);
        morePicturesBtn.click();
    }

    @Step(value = "Click on category {name}")
    public void clickPictureCategory(String name) {
        WebElement pictureCategoryBtn = null;
        if (name.equals("Вышитые картины")) {
            pictureCategoryBtn = embroideredPictureBtn;
        } else if (name.equals("Батик")) {
            pictureCategoryBtn = batikPictureBtn;
        } else if (name.equals("Ювелирное искусство")) {
            pictureCategoryBtn = jewelryArt;
        }

        waitElementToBeClickable(pictureCategoryBtn);
        pictureCategoryBtn.click();
    }

    @Step(value = "Click on genre {name}")
    public void clickPictureGenre(String name) {
        WebElement pictureGenreBtn = null;
        if (name.equals("Городской пейзаж")) {
            pictureGenreBtn = urbanLandscapeBtn;
        }
        waitElementToBeClickable(pictureGenreBtn);
        pictureGenreBtn.click();
    }

    @Step(value = "Click on apply button")
    public void clickApplyBtn() {
        waitElementToBeClickable(applyBtn);
        applyBtn.click();
    }

    @Step(value = "Search picture: {search}")
    public void searchPicture(String search) {
        mainInputSearch.sendKeys(search);
        getActions().keyDown(Keys.ENTER).perform();
        waitElementToBeVisible(secondaryInputSearch);
        waitElementAttributeToBe(secondaryInputSearch, "value", search);
    }
}
