package ie.williamswalsh.dropdowns;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Dynamic dropdown is a dropdown where the options are not loaded until the dropdown is opened.
 * It may involve fetching data.
 * The data may be filtered from other inputs on the website.
 */
public class DynamicDropdownTest {

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
        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@value='BLR']")).click(); // Select from "Belaguru Airport"
        Thread.sleep(1000);
        // "(//a[@value='MAA'])[2]" -> Select second instance of xpath on page
//        driver.findElement(By.xpath("(//a[@value='MAA'])[2]")).click(); // Select to "Chennai Airport"
//        Can use xpath inside xpath -> first xpath scopes the second xpath - space separated
        driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='MAA']")).click();
    }

    @Test
    void countryDropdownTest() throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
        driver.findElement(By.id("autosuggest")).sendKeys("ind");
        Thread.sleep(1000);
        List<WebElement> webElements = driver.findElements(By.xpath("//li[@class='ui-menu-item']/a"));

//        for (WebElement elem: webElements) {
//            if(elem.getText().equalsIgnoreCase("India")) {
//                elem.click();
//                break;
//            }
//        }
//      OR:

        // Click each web elem which contains the insensitive text India.
        driver.findElements(By.xpath("//li[@class='ui-menu-item']/a"))
                .stream().filter((e) -> e.getText().equalsIgnoreCase("India"))
                .findFirst()
                .get().click();
    }

    @Test
    void clickCheckboxTest() throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

        driver.findElement(By.id("ctl00_mainContent_chk_SeniorCitizenDiscount")).click();
        boolean isSnrCitizenDiscountChecked = driver.findElement(By.id("ctl00_mainContent_chk_SeniorCitizenDiscount")).isSelected();
        assertTrue(isSnrCitizenDiscountChecked);
        System.out.println("Senior Citizen Discount: " + isSnrCitizenDiscountChecked);

        int checkboxesCount = driver.findElements(By.xpath("//*[@type='checkbox']")).size();
        assertEquals(6, checkboxesCount);
        Thread.sleep(1000);

    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
