package ie.williamswalsh.dropdowns;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StaticDropDownTest {

    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
    }

    @Test
    void staticDropdownTest() {
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

//        Static dropdowns have a select tag
//        They are static in that their options don't change index once its created.
        Select dropdown = new Select(driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency")));
        dropdown.selectByIndex(3);
        assertEquals("USD", dropdown.getFirstSelectedOption().getText());

//        This dropdown is a single item drop down - it doesn't allow multiple item selection.
        dropdown.selectByValue("INR");
        assertEquals("INR", dropdown.getFirstSelectedOption().getText());
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
