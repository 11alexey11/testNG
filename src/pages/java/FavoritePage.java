import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FavoritePage extends BasePage {
    public FavoritePage(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver, webDriverWait);
    }
    @Step(value = "Get first picture name")
    public String getFirstPictureName() {
        return findElement("//div[@class='post']//div[@class='ssize']").getText();
    }
}
