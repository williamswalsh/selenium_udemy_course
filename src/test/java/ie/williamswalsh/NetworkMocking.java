package ie.williamswalsh;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;

import java.io.IOException;

public class NetworkMocking {

    private static WebDriver d;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        d = new ChromeDriver();
        DevTools devTools = ((HasDevTools) d).getDevTools();
        devTools.createSession();

    }

    @Test
    void takeScreenShotTest() throws IOException {
        d.get("https://google.com");
    }

    @AfterAll
    static void tearDown() {
        d.quit();
    }
}
