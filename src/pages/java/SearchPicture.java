import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SearchPicture {
    private final WebDriver driver;
    private final WebDriverWait wait;
    public SearchPicture(WebDriver webDriver, WebDriverWait webDriverWait) {
        driver = webDriver;
        wait = webDriverWait;
    }

    public void checkSearchingPicture(String search) {
        WebElement inputSearch = driver.findElement(By.xpath("//input[@name='qs']"));
        inputSearch.sendKeys(search);
        new Actions(driver).keyDown(Keys.ENTER).perform();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//input[@class='inp']"), "value", search));
        String actualPictureName = driver.findElement(By.xpath("//div[@class='post']//div[@class='ssize']")).getAttribute("textContent");

        Assert.assertFalse(actualPictureName.contains(search));
    }
}
