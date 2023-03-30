import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class PicturesPage extends BasePage {

    public static String expectedPictureName;
    static List<String> expectedPictureNames = new ArrayList<>();
    static List<String> expectedPicturePrices = new ArrayList<>();
    public PicturesPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver, webDriverWait);
    }

    @Step(value = "Check number of pictures by name")
    public Integer checkNumberPictures(String name) {
        By xPath = By.xpath("//div[@class='post']//div[@class='ssize']");
        waitElementsToBeVisible(xPath);
        List<WebElement> pictures = findElements(String.format("//div[@class='post']//div[@class='ssize' and text()='%s']", name));
        return pictures.size();
    }

    @Step(value = "Click picture")
    public void clickPicture(String name) {
        By xPath = By.xpath("//div[@class='post']//div[@class='ssize']");
        waitElementsToBeVisible(xPath);
        WebElement picture = findElement(String.format("//div[@class='post']//div[@class='ssize' and text()='%s']/../..", name));
        waitElementToBeClickable(picture);
        picture.click();
    }

    @Step(value = "Check picture style")
    public String checkPictureStyle() {
        WebElement pictureStyle = findElement("//div[@class='txtline lft']//span[contains(text(), 'Стиль')]/following-sibling::node()");
        return pictureStyle.getText();
    }

    @Step(value = "Add picture to favorite")
    public void addPictureToFavorite() {
        expectedPictureName = findElement("//div[@class='post']//div[@class='ssize']").getText();
        WebElement favoriteBtn = findElements("//div[@class='heart']").get(0);
        waitElementToBeClickable(favoriteBtn);
        favoriteBtn.click();
    }

    @Step(value = "Move to favorite page")
    public void moveToFavoritePage() {
        WebElement favoriteBtn = findElement("//span[@class='fvtico']");
        waitElementToBeClickable(favoriteBtn);
        favoriteBtn.click();
    }

    @Step(value = "Check first picture name")
    public String actualFirstPictureName() {
        return findElement("//div[@class='post']//div[@class='ssize']").getAttribute("textContent");
    }

    @Step(value = "Add picture to bin")
    public void addPictureToBin(Integer size) {
        List<WebElement> pictureNames = findElements("//div[@class='post']//div[@class='ssize']");
        List<WebElement> picturePrices = findElements("//div[@class='price' and @itemprop='offers']");
        List<WebElement> buyBtns = findElements("//div[@class='post']//div[@class='oclick' and text()=' Купить']");
        expectedPicturePrices.clear();
        expectedPictureNames.clear();
        for (int i = 0; i < size; i++) {
            expectedPictureNames.add(i, pictureNames.get(i).getAttribute("textContent"));
            expectedPicturePrices.add(i, picturePrices.get(i).getText());
            buyBtns.get(i).click();
            WebElement continueButton = findElement("//button[@class='continue']");
            waitElementToBeClickable(continueButton);
            continueButton.click();
        }
    }

    @Step(value = "Move to bin page")
    public void moveToBinPage() {
        WebElement binBtn = findElement("//span[@class='basketico']");
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
