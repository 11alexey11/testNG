import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FavoritePage extends BasePage {
    @FindBy(xpath = "//div[@class='post']//div[@class='ssize']")
    WebElement picture;

    public FavoritePage(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver, webDriverWait);
    }
    @Step(value = "Get first picture name")
    public String getFirstPictureName() {
        return picture.getText();
    }
}
