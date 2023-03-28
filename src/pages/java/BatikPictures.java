import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class BatikPictures {
    private final WebDriver driver;
    static String expectedPictureName;
    public BatikPictures(WebDriver webDriver) {
        driver = webDriver;
    }

    public void moveToBatikPictures() {
        WebElement showBtn = driver.findElement(By.xpath("//li[@class='menu-group gids']"));
        showBtn.click();
        WebElement picture = driver.findElement(By.xpath("//li[@class='menu-extra-item gids']//*[text()=' Батик']"));
        picture.click();
        expectedPictureName = driver.findElement(By.xpath("//div[@class='post']//div[@class='ssize']")).getText();
        WebElement firstPictureFavorite = driver.findElements(By.xpath("//div[@class='heart']")).get(0);
        firstPictureFavorite.click();
    }
    //div[@class='post']
    public void checkNumberOfItemsInFavorites() {
        WebElement favoriteCounter = driver.findElement(By.xpath("//span[@class='cntfvt']"));

        Assert.assertEquals(favoriteCounter.getText(), "1");
    }

    public void checkSavingToFavorites() {
        WebElement favoriteCounter = driver.findElement(By.xpath("//span[@class='cntfvt']"));
        favoriteCounter.click();
        String actualPictureName = driver.findElement(By.xpath("//div[@class='post']//div[@class='ssize']")).getText();

        Assert.assertEquals(actualPictureName, expectedPictureName);
    }
}
