package ie.williamswalsh.actions;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MouseOverTest {


    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    void actionTests() {
        driver.get("https://amazon.co.uk");

//        MouseOver displays new dialog.
        WebElement accountList = driver.findElement(By.id("nav-link-accountList"));
        Actions actions = new Actions(driver);
//        Must build & perform
        actions.moveToElement(accountList)
                .build()
                .perform();

//        type HELLO into search bar
        actions.moveToElement(driver.findElement(By.id("twotabsearchtextbox")))
                .click()
                .keyDown(Keys.SHIFT)
                .sendKeys("hello")
                .doubleClick()
                .contextClick()
                .build()
                .perform();

//
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
