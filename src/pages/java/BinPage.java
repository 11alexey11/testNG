import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Optional;

public class BinPage extends BasePage {
    @FindBy(xpath = "//div[@class='c_name' and not(contains(text(), 'Автор: '))]")
    List<WebElement> pictureNames;

    @FindBy(xpath = "//div[@class='shop']//div[@class='price']")
    List<WebElement> picturePrices;

    public BinPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver, webDriverWait);
    }

    @Step(value = "Check price and name pictures")
    public Boolean checkPicturesNamesPrices(List<String> expectedNames, List<String> expectedPrices) {
        int actualMatchesNames = 0;
        int actualMatchesPrices = 0;
        int pictureCount = expectedNames.size();

        for (int i = 0; i < pictureCount; i++) {
            String expectedPictureName = expectedNames.get(i);
            System.out.println(pictureNames.get(i).getAttribute("textContent"));
            Optional<WebElement> obj = pictureNames.stream()
                    .filter(element -> expectedPictureName.contains(element.getAttribute("textContent"))).findFirst();
            if (obj.isPresent()) {
                actualMatchesNames += 1;
            }
        }

        if (actualMatchesNames == pictureCount) {
            System.out.println("Я тут");

            for (int i = 0; i < pictureCount; i++) {
                String expectedPicturePrice = expectedPrices.get(i);
                Optional<WebElement> obj = picturePrices.stream()
                        .filter(element -> expectedPicturePrice.equals(element.getAttribute("textContent"))).findFirst();
                if (obj.isPresent()) {
                    actualMatchesPrices += 1;
                }
            }

            return actualMatchesNames == actualMatchesPrices;
        } else return false;
    }
}
