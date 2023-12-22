package ie.williamswalsh.frames;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandlingFramesTest {

    private static WebDriver d;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        d = new ChromeDriver();
    }

    @Test
    void actionTests() {
        d.get("http://jqueryui.com/droppable/");

//        Get count of iframes in page.
        assertEquals(1, d.findElements(By.xpath("//iframe")).size());

//        Container is inside Frame - so cannot click draggable
//        d.findElement(By.id("draggable']")).click();
//        need to tell selenium that its in a frame.
//        N.B: Independent dom
        WebElement frame = d.findElement(By.xpath("//iframe[@class='demo-frame']"));
        d.switchTo().frame(frame);


//        Drag and drop
        Actions a = new Actions(d);
        WebElement src = d.findElement(By.id("draggable"));
        WebElement target = d.findElement(By.id("droppable"));
        a.dragAndDrop(src, target)
                .build()
                .perform();

        assertEquals("Dropped!", target.getText());
    }

    @AfterAll
    static void tearDown() {
        d.quit();
    }
}
