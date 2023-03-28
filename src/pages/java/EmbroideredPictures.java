import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class EmbroideredPictures {
    private final WebDriver driver;
    private final WebDriverWait wait;
    public EmbroideredPictures(WebDriver webDriver, WebDriverWait webDriverWait) {
        driver = webDriver;
        wait = webDriverWait;
    }

    private void moveToEmbroideredPictures() {
        WebElement showBtn = driver.findElement(By.xpath("//li[@class='menu-group gids']"));
        wait.until(ExpectedConditions.elementToBeClickable(showBtn)).click();
        WebElement picture = driver.findElement(By.xpath("//li[@class='menu-extra-item gids']//*[text()=' Вышитые картины']"));
        picture.click();
        WebElement cityCheckbox = driver.findElement(By.xpath("//input[@name='genre257']"));
        cityCheckbox.click();
        WebElement applyChanges = driver.findElement(By.xpath("//div[@id='applymsg']"));
        applyChanges.click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='post']//div[@class='ssize']")));
    }
    public void checkPictureName(String name) {
        this.moveToEmbroideredPictures();
        List<WebElement> pictures = driver.findElements(By.xpath(String.format("//div[@class='post']//div[@class='ssize' and text()='%s']", name)));

        Assert.assertEquals(pictures.size(), 1);
    }

    public void checkPictureStyle(String style, String name) {
        this.moveToEmbroideredPictures();
        WebElement picture = driver.findElement(By.xpath(String.format("//div[@class='post']//div[@class='ssize' and text()='%s']/../..", name)));
        picture.click();
        WebElement styleElement = driver.findElement(By.xpath("//div[@class='txtline lft']//span[contains(text(), 'Стиль')]/following-sibling::node()"));

        Assert.assertEquals(styleElement.getText(), style);
    }
}
