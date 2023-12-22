package ie.williamswalsh.actions;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChildParentWindowTest {


    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    void actionTests() {
        driver.get("https://rahulshettyacademy.com/loginpagePractise/#/");
        driver.findElement(By.xpath("//a[@class='blinkingText']")).click();

        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator(); // [parentId, childId]
        String parentId = it.next();
        String childId = it.next();

//        Move to view child window.
        driver.switchTo().window(childId);
        String redParagraph = driver.findElement(By.xpath("//p[@class='im-para red']")).getText();
        String actualEmail = redParagraph.split(" ")[4];
        String expectedEmail = "mentor@rahulshettyacademy.com";
        assertEquals(expectedEmail, actualEmail);

//        Move to view parent window.
        driver.switchTo().window(parentId);
        driver.findElement(By.id("username")).sendKeys(actualEmail);
    }
//
//    @AfterAll
//    static void tearDown() {
//        driver.quit();
//    }
}
