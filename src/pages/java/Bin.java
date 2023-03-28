import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Bin {
    private final WebDriver driver;
    static String expectedPictureName;
    static String expectedPicturePrice;
    public Bin(WebDriver webDriver) {
        driver = webDriver;
    }

    public void moveToDefinitePictures(String name) {
        WebElement showBtn = driver.findElement(By.xpath("//li[@class='menu-group gids']"));
        showBtn.click();
        WebElement picture = driver.findElement(By.xpath(String.format("//li[@class='menu-extra-item gids']//*[text()=' %s']", name)));
        picture.click();
        expectedPictureName = driver.findElement(By.xpath("//div[@class='post']//div[@class='ssize']")).getAttribute("textContent");
        expectedPicturePrice = driver.findElement(By.xpath("//div[@class='price' and @itemprop='offers']")).getText();
        WebElement buyBtn = driver.findElement(By.xpath("//div[@class='post']//div[@class='oclick' and text()=' Купить']"));
        buyBtn.click();
        WebElement continueBtn = driver.findElement(By.xpath("//button[@class='continue']"));
        continueBtn.click();
    }

    public void checkNumberOfItemsInBin() {
        WebElement binCounter = driver.findElement(By.xpath("//span[@class='cntcart']"));

        Assert.assertEquals(binCounter.getText(), "1");
    }

    public void checkSavingToBin() {
        WebElement binCounter = driver.findElement(By.xpath("//span[@class='cntcart']"));
        binCounter.click();
        String actualPictureName = driver.findElement(By.xpath("//div[@class='c_name']")).getAttribute("textContent");
        String actualPicturePrice = driver.findElement(By.xpath("//div[@class='shop']//div[@class='price']")).getAttribute("textContent");

        Assert.assertTrue(expectedPictureName.contains(actualPictureName));
        Assert.assertEquals(expectedPicturePrice, actualPicturePrice);
    }
}
