import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Driver {

    static final Integer TIMEOUT_SECONDS = 10;
    public static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    public static ThreadLocal<WebDriverWait> webDriverWaitThreadLocal = new ThreadLocal<>();

    public static WebDriverWait getWebDriverWait() {
        return webDriverWaitThreadLocal.get();
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public WebDriver initialize(String browser) {
        System.out.println("Thread: " + Thread.currentThread().getId());
        if (browser.equalsIgnoreCase("chrome")) {
//            System.setProperty("webdriver.chrome.driver", "D:\\tools\\chromedriver\\chromedriver.exe");
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*");
            driverThreadLocal.set(new ChromeDriver(chromeOptions));

        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            driverThreadLocal.set(new FirefoxDriver(firefoxOptions));
        }
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TIMEOUT_SECONDS));
        webDriverWaitThreadLocal.set(new WebDriverWait(getDriver(), Duration.ofSeconds(TIMEOUT_SECONDS)));
        getDriver().get("https://artnow.ru/");
        return getDriver();
    }
}
