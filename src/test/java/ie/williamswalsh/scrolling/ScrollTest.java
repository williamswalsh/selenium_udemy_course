package ie.williamswalsh.scrolling;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Can scroll browser window by entering into browser console:
 * window.scrollBy(0,500) (h,v) 500pixels vertically
 */
public class ScrollTest {

    private static WebDriver d;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        d = new ChromeDriver();
    }

    @Test
    void scrollDocumentThenScrollTableTest() throws InterruptedException {
        d.get("https://rahulshettyacademy.com/AutomationPractice/");
        JavascriptExecutor executor = (JavascriptExecutor)d;
        executor.executeScript("window.scrollBy(0,500)");
        Thread.sleep(3000);
        executor.executeScript("document.querySelector('.tableFixHead').scrollTop=5000");
    }

    @AfterAll
    static void tearDown() {
        d.quit();
    }
}

