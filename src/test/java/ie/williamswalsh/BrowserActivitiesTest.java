package ie.williamswalsh;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrowserActivitiesTest {
    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
//        This env property setting is optional from selenium 4.6.0+
//        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    void childToParent() {
        driver.manage().window().maximize(); // Maximise window
        driver.get("https://google.com"); // Browse to Google
        driver.get("https://rahulshettyacademy.com/AutomationPractice/"); // move from google to rahul's site

//        driver.navigate().to() -> synonym for .get()
        driver.navigate().back();       // Navigate back to google
        driver.navigate().forward();    // Navigate forward to rahul's site

//        String downloadDocText = driver.findElement(By.xpath("")).getText();
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
