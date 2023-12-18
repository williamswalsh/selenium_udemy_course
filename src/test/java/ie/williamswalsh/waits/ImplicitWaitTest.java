package ie.williamswalsh.waits;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class ImplicitWaitTest {

    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();

    }
    @Test
    void name() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.of(ChronoUnit.SECONDS));
    }
}
