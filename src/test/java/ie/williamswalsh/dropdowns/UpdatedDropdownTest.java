package ie.williamswalsh.dropdowns;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdatedDropdownTest {

    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
    }

    @Test
    void adultSelectionDropdownTest() throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        driver.findElement(By.id("divpaxinfo")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("hrefIncAdt")).click();
        driver.findElement(By.id("hrefIncAdt")).click();
        driver.findElement(By.id("btnclosepaxoption")).click();
        assertEquals("3 Adult", driver.findElement(By.id("divpaxinfo")).getText());
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
