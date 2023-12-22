package ie.williamswalsh;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;

public class NewTabTest {
    private static WebDriver d;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        d = new ChromeDriver();
    }

    @Test
    void switchToNewTab() {
        d.get("https://the-internet.herokuapp.com");
        d.switchTo().newWindow(WindowType.TAB);
    }

    @Test
    void switchToNewWindow() {
        d.get("https://the-internet.herokuapp.com");
        d.switchTo().newWindow(WindowType.WINDOW);
    }

    @AfterAll
    static void tearDown() {
        d.quit();
    }
}
