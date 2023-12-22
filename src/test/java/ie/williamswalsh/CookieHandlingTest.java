package ie.williamswalsh;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CookieHandlingTest {
    private static WebDriver d;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        d = new ChromeDriver(options);
    }

    @Test
    void deleteAllCookiesBeforeTest() {
        d.get("https://google.com/");
        d.manage().deleteAllCookies(); // N.B.: Can force that the cookie is deleted causing the user to be logged out.
//        d.manage().deleteCookieNamed("sessionKey");
    }

    @AfterAll
    static void tearDown() {
        d.quit();
    }
}
