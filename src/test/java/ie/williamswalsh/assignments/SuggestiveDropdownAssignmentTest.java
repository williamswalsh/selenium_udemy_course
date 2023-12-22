package ie.williamswalsh.assignments;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SuggestiveDropdownAssignmentTest {

    private static WebDriver d;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        d = new ChromeDriver();
    }

    @Test
    void actionTests() throws InterruptedException {
        d.get("https://rahulshettyacademy.com/AutomationPractice/");
        WebElement suggestiveDropdown = d.findElement(By.xpath("//input[@id='autocomplete']"));
        suggestiveDropdown.sendKeys("ire");
        Thread.sleep(500);
        String arrowDown = Keys.chord(Keys.ARROW_DOWN);
        String enter = Keys.chord(Keys.ENTER);
        suggestiveDropdown.sendKeys(arrowDown);
        suggestiveDropdown.sendKeys(arrowDown);
        suggestiveDropdown.sendKeys(enter);

        assertEquals("Ireland", suggestiveDropdown.getAttribute("value"));
    }

    @AfterAll
    static void tearDown() {
        d.quit();
    }
}
