package ie.williamswalsh.assignments;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrameHandlingTest {

    private static WebDriver d;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        d = new ChromeDriver();
    }

    @Test
    void actionTests() {
        d.get("https://the-internet.herokuapp.com");
        d.findElement(By.xpath("//li/a[@href='/nested_frames']")).click();
        WebElement topFrame = d.findElement(By.xpath("/html/frameset/frame[@src='/frame_top']"));
        d.switchTo().frame(topFrame);

        WebElement topMiddleFrame = d.findElement(By.xpath("//frameset/frame[@src='/frame_middle']"));
        d.switchTo().frame(topMiddleFrame);

        System.out.println(d.findElement(By.id("content")).getText());
    }

    @AfterAll
    static void tearDown() {
        d.quit();
    }
}
