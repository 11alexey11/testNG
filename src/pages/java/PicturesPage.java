import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class PicturesPage extends BasePage {
    @FindBy(xpath = "//div[@class='post']//div[@class='ssize' and text()='Трамвайный путь']")
    List<WebElement> tramTrackPictures;

    @FindBy(xpath = "//div[@class='post']//div[@class='ssize']")
    List<WebElement> pictures;

    @FindBy(xpath = "//div[@class='price' and @itemprop='offers']")
    List<WebElement> prices;

    @FindBy(xpath = "//div[text()=' Купить']")
    List<WebElement> buyBtns;

    @FindBy(xpath = "//div[@class='post']//div[@class='ssize' and text()='Трамвайный путь']/../..")
    WebElement tramTrackPictureBtn;

    @FindBy(xpath = "//div[@class='txtline lft']//span[contains(text(), 'Стиль')]/following-sibling::node()")
    WebElement pictureStyle;

    @FindBy(xpath = "//div[@class='heart']")
    List<WebElement> favoriteBtns;

    @FindBy(xpath = "//span[@class='fvtico']")
    WebElement favoriteBtn;

    @FindBy(xpath = "//span[@class='basketico']")
    WebElement binBtn;

    @FindBy(xpath = "//button[@class='continue']")
    WebElement continueBtn;

    public static String expectedPictureName;
    static List<String> expectedPictureNames = new ArrayList<>();
    static List<String> expectedPicturePrices = new ArrayList<>();
    public PicturesPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver, webDriverWait);
    }

    @Step(value = "Check number of pictures by name")
    public Integer checkNumberPictures(String name) {
        List<WebElement> pictures = null;
        if (name.equals("Трамвайный путь")) {
            pictures = tramTrackPictures;
        }
        waitElementsToBeVisible(pictures);
        return pictures.size();
    }

    @Step(value = "Click picture")
    public void clickPicture(String name) {
        WebElement pictureBtn = null;
        if (name.equals("Трамвайный путь")) {
            pictureBtn = tramTrackPictureBtn;
        }
        waitElementsToBeVisible(pictures);
        waitElementToBeClickable(pictureBtn);
        pictureBtn.click();
    }

    @Step(value = "Check picture style")
    public String checkPictureStyle() {
        return pictureStyle.getText();
    }

    @Step(value = "Add picture to favorite")
    public void addPictureToFavorite() {
        expectedPictureName = pictures.get(0).getText();
        WebElement favoriteBtn = favoriteBtns.get(0);
        waitElementToBeClickable(favoriteBtn);
        favoriteBtn.click();
    }

    @Step(value = "Move to favorite page")
    public void moveToFavoritePage() {
        waitElementToBeClickable(favoriteBtn);
        favoriteBtn.click();
    }

    @Step(value = "Check first picture name")
    public String actualFirstPictureName() {
        return pictures.get(0).getAttribute("textContent");
    }

    @Step(value = "Add picture to bin")
    public void addPictureToBin(Integer size) {
        expectedPicturePrices.clear();
        expectedPictureNames.clear();
        List<WebElement> buyBtns = findElements("//div[@class='post']//div[@class='oclick' and text()=' Купить']");
        for (int i = 0; i < size; i++) {
            expectedPictureNames.add(i, pictures.get(i).getAttribute("textContent"));
            expectedPicturePrices.add(i, prices.get(i).getText());
            buyBtns.get(i).click();
            waitElementToBeClickable(continueBtn);
            continueBtn.click();
        }
    }

    @Step(value = "Move to bin page")
    public void moveToBinPage() {
        waitElementToBeClickable(binBtn);
        binBtn.click();
    }

    public String getExpectedPictureName() {
        return expectedPictureName;
    }

    public List<String> getExpectedPictureNames() {
        return expectedPictureNames;
    }

    public List<String> getExpectedPicturePrices() {
        return expectedPicturePrices;
    }
}
