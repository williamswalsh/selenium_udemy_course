package ie.williamswalsh;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Firefox uses the Gecko driver.
 */
public class FirefoxLaunchTest {

    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.gecko.driver", "/Users/legoman/code/selenium/drivers/geckodriver");
        driver = new FirefoxDriver();
    }

    @Test
    public void getUrlEndpoint() {
        driver.get("https://rahulshettyacademy.com");
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
