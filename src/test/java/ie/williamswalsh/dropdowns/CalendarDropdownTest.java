package ie.williamswalsh.dropdowns;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class CalendarDropdownTest {

    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    void name() throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@value='BLR']")).click(); // Select from "Belaguru Airport"
        Thread.sleep(1000);

//        Select Chennai airport
        driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='MAA']")).click();
        Thread.sleep(1000);

//        Select current date
        driver.findElement(By.cssSelector(".ui-state-default.ui-state-highlight")).click();
    }

    @Test
    void isElementEnabledTest() throws InterruptedException {
//        Selenium doesn't correctly detect if an element is enabled.
//        It always says element is enabled in this case.
//        Even though it is disabled before the radio button click
//        and enabled afterwards.
//        Its just always enabled - isEnabled returns true
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        Thread.sleep(2000);
        WebElement calendar = driver.findElement(By.id("Div1"));
        assertTrue(calendar.isEnabled());
        assertFalse(isCalendarEnabled(calendar)); // Not enabled yet

        driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();
        assertTrue(calendar.isEnabled());
        assertTrue(isCalendarEnabled(calendar));   // We detect 0.5 string in the style attribute, which is set by opacity string.
    }

    boolean isCalendarEnabled(WebElement element) {
        return !element.getAttribute("style").contains("0.5");
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}

