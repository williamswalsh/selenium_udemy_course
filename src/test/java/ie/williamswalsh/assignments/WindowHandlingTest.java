package ie.williamswalsh.assignments;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WindowHandlingTest {

    private static WebDriver d;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        d = new ChromeDriver();
    }

    @Test
    void actionTests() {
        d.get("https://the-internet.herokuapp.com");
        d.findElement(By.xpath("//li/a[@href='/windows']")).click();

        d.findElement(By.xpath("//div[@class='example']/a[@href='/windows/new']")).click();

        Set<String> windows = d.getWindowHandles();
        Iterator<String> it = windows.iterator(); // [parentId, childId]
        String parentId = it.next();
        String childId = it.next();

        d.switchTo().window(childId);

        String newWindowText = d.findElement(By.xpath("//div[@class='example']/h3")).getText();
        System.out.println(newWindowText);
        assertEquals("New Window", newWindowText);

        d.switchTo().window(parentId);

        String originalWindowText = d.findElement(By.xpath("//div[@class='example']/h3")).getText();
        System.out.println(originalWindowText);
        assertEquals("Opening a new window", originalWindowText);
    }

    @AfterAll
    static void tearDown() {
        d.quit();
    }
}
