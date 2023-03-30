import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage(WebDriver webDriver, WebDriverWait webDriverWait) {
        driver = webDriver;
        wait = webDriverWait;
    }

    protected Actions getActions() {
        return new Actions(driver);
    }

    public WebElement findElement(String xPath) {
        return driver.findElement(By.xpath(xPath));
    }

    public List<WebElement> findElements(String xPath) {
        return driver.findElements(By.xpath(xPath));
    }

    public void waitElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitElementsToBeVisible(By xPath) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(xPath));
    }

    public void waitElementToBeVisible(By xPath) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(xPath));
    }

    public  void waitElementAttributeToBe(WebElement element, String attribute, String value) {
        wait.until(ExpectedConditions.attributeToBe(element, attribute, value));
    }
}
