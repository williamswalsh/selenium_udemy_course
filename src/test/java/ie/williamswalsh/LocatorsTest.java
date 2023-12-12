package ie.williamswalsh;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocatorsTest {

    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    void doBadLogin() {
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        driver.findElement(By.id("inputUsername"))
                .sendKeys("rahul");

        driver.findElement(By.name("inputPassword"))
                .sendKeys("bad_password");

        driver.findElement(By.className("signInBtn"))
                .click();

//        Error text not visible yet need to add a delay.
//        Implicit wait 3 seconds
//        Thread.sleep(3000);
//        OR
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        // Css selector
        // element_name#id
        // OR
        // element_name.class_name
        // p.error
        // button.signInBtn
        // OR
        // element_name[attribute='value']
        // input[placeholder='Username']
        String errorText = driver.findElement(By.cssSelector("p.error"))
                .getText();

//        Using console dev tools tab you can find the element by using the syntax:
//        $('CSS_SELECTOR')
//        $('p.error')
//        This shows the elements on webpage that match that selector.

        assertEquals("* Incorrect username or password", errorText);
    }

    @Test
    void doGoodLogin() {
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        driver.findElement(By.id("inputUsername"))
                .sendKeys("rahul");

        driver.findElement(By.cssSelector("input[placeholder='Password']"))
                .sendKeys("rahulshettyacademy");

        driver.findElement(By.cssSelector("#chkboxOne"))
                        .click();

        driver.findElement(By.cssSelector("#chkboxTwo"))
                .click();

//        driver.findElement(By.className("signInBtn"))
//                .click();
//      OR
        driver.findElement(By.xpath("//button[contains(@class, 'submit')]")).click();
    }

    @Test
    void findByTest() throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        driver.findElement(By.linkText("Forgot your password?")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        // Xpath
        //- FORMAT:  //Tagname[@attribute='value']
        //- EXAMPLE: //input[placeholder='Name']
        driver.findElement(By.xpath("//input[@placeholder='Name']"))
                .sendKeys("Elliot");

        driver.findElement(By.xpath("//input[@placeholder='Email']"))
                .sendKeys("mr_robot@gmail.com");

//        can you index to select occurrence of element that you want [2]
//        //input[@type='text'][2] - NB: non-zero based index
        driver.findElement(By.xpath("//input[@type='text'][2]"))
                .clear();

        driver.findElement(By.xpath("//input[@placeholder='Email']"))
                .sendKeys("mr.anderson@gmail.com");

        driver.findElement(By.xpath("//form/input[3]"))
                .sendKeys("9864353253");


//        Button is not clickable, some other element is receiving the click.
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        implicit wait didn't work - thread sleep did work??
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".reset-pwd-btn"))
                .click();
        String temporaryPasswordMessage = driver.findElement(By.cssSelector("form p"))
                .getText();
        assertEquals("Please use temporary password 'rahulshettyacademy' to Login.", temporaryPasswordMessage);
    }

//
//    @AfterAll
//    static void tearDown() {
//        // Only closes the current window
//        driver.close();
//        // closes all associates windows/tabs
//        driver.quit();
//    }
}
