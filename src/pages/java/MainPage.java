import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage {
    public MainPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver, webDriverWait);
    }

    @Step(value = "Click on more pictures")
    public void clickMorePictures() {
        WebElement morePicturesBtn = findElement("//li[@class='menu-group gids']");
        waitElementToBeClickable(morePicturesBtn);
        morePicturesBtn.click();
    }

    @Step(value = "Click on category {name}")
    public void clickPictureCategory(String name) {
        WebElement pictureCategoryBtn = findElement(String.format("//li[@class='menu-extra-item gids']//*[text()=' %s']", name));
        waitElementToBeClickable(pictureCategoryBtn);
        pictureCategoryBtn.click();
    }

    @Step(value = "Click on genre {name}")
    public void clickPictureGenre(String name) {
        WebElement pictureGenreBtn = findElement(String.format("//label[text()=' %s']", name));
        waitElementToBeClickable(pictureGenreBtn);
        pictureGenreBtn.click();
    }

    @Step(value = "Click on apply button")
    public void clickApplyBtn() {
        WebElement applyBtn = findElement("//div[@id='applymsg']");
        waitElementToBeClickable(applyBtn);
        applyBtn.click();
    }

    @Step(value = "Search picture: {search}")
    public void searchPicture(String search) {
        WebElement inputSearch = findElement("//input[@name='qs']");
        inputSearch.sendKeys(search);
        getActions().keyDown(Keys.ENTER).perform();
        waitElementToBeVisible(By.xpath("//input[@class='inp']"));
        WebElement otherInputSearch = findElement("//input[@class='inp']");
        waitElementAttributeToBe(otherInputSearch, "value", search);
    }
}
