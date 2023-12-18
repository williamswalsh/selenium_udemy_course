package ie.williamswalsh.assignments;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ElectoronicsStoreTest {

    private static WebDriver driver;

    private static WebDriverWait wait;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    void loginAndBuyEverythingTest() {
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        driver.findElement(By.id("username")).sendKeys("rahulshettyacademy");
        driver.findElement(By.id("password")).sendKeys("learning");
        driver.findElement(By.xpath("//div[@class='form-check-inline']/label[2]/span[@class='checkmark']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("okayBtn")));
        driver.findElement(By.id("okayBtn")).click();

        Select userType = new Select(driver.findElement(By.xpath("//select[@class='form-control']")));
        userType.selectByValue("consult");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("terms")));
        driver.findElement(By.id("terms")).click();
        driver.findElement(By.id("signInBtn")).click();

//        Wait until page loads fully
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='zmdi zmdi-shopping-cart']")));

        List<WebElement> productAddBtns = driver.findElements(By.xpath("//i[@class='zmdi zmdi-shopping-cart']"));
        for (WebElement addBtn: productAddBtns ) {
            addBtn.click();
        }

        driver.findElement(By.xpath("//li[@class='nav-item active']/a")).click();
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
