package ie.williamswalsh;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlertTest {

    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    void fillOutAlertFormTest() {
        String userName = "William Walsh";
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.findElement(By.id("name")).sendKeys(userName);
        driver.findElement(By.id("alertbtn")).click();

//        Switch context and get alert message.
        String alertMsg = driver.switchTo().alert().getText();
        String expectedAlertMsg = "Hello " + userName + ", share this practice page and share your knowledge";
        assertEquals(expectedAlertMsg, alertMsg);

//        Switch context to alerts then accept the alert.
        driver.switchTo().alert().accept();
//        Can cancel alert using -> alert().dismiss()
    }

    @Test
    void fillOutConfirmAlertFormTest() {
        String userName = "William Walsh";
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

//        Click confirm btn with no input.
        driver.findElement(By.id("confirmbtn")).click();
//        Cancel the alert.
        driver.switchTo().alert().dismiss();
//        Input name into form.
        driver.findElement(By.id("name")).sendKeys(userName);
//        Click confirm btn with input.
        driver.findElement(By.id("confirmbtn")).click();

//        Switch context and get alert message.
        String alertMsg = driver.switchTo().alert().getText();
        String expectedAlertMsg = "Hello " + userName + ", Are you sure you want to confirm?";
        assertEquals(expectedAlertMsg, alertMsg);

//        Confirm(accept) the alert dialog.
        driver.switchTo().alert().accept();
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
