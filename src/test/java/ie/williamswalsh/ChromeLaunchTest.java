package ie.williamswalsh;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChromeLaunchTest {

    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    void getUrlEndpoint() {
        driver.get("https://rahulshettyacademy.com");
    }

    @Test
    void getTitleOfWebpage() {
        driver.get("https://rahulshettyacademy.com");
        assertEquals("Selenium, API Testing, Software Testing & More QA Tutorials | Rahul Shetty Academy", driver.getTitle());
    }

    @Test
    void checkForNoUrlRedirects() {
        String correctUrl = "https://rahulshettyacademy.com/";
        driver.get(correctUrl);
        assertEquals(correctUrl, driver.getCurrentUrl());
    }

    @AfterAll
    static void tearDown() {
        // Only closes the current window
        driver.close();
        // closes all associates windows/tabs
        driver.quit();
    }
}
