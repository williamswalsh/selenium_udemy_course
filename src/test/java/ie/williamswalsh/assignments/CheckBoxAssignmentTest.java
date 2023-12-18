package ie.williamswalsh.assignments;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class CheckBoxAssignmentTest {

    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    void name() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice");

        driver.findElement(By.id("checkBoxOption1")).click();
        assertTrue(driver.findElement(By.id("checkBoxOption1")).isSelected());

        driver.findElement(By.id("checkBoxOption1")).click();
        assertFalse(driver.findElement(By.id("checkBoxOption1")).isSelected());

        int checkboxesCount = driver.findElements(By.xpath("//*[@type='checkbox']")).size();
        assertEquals(3, checkboxesCount);
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}

