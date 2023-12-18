package ie.williamswalsh.assignments;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class AngularPracticeAssignmentTest {

    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    void fillOutFormTest() {
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys("William Walsh");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("fake_email@site.com");
        driver.findElement(By.id("exampleInputPassword1")).sendKeys("fake_password");
        driver.findElement(By.id("exampleCheck1")).click();

        Select genderDropdown = new Select(driver.findElement(By.id("exampleFormControlSelect1")));
        genderDropdown.selectByIndex(0);

        driver.findElement(By.id("inlineRadio1")).click();
        driver.findElement(By.cssSelector("input[name='bday'")).sendKeys("01/01/2000");
        driver.findElement(By.cssSelector(".btn.btn-success")).click();
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
