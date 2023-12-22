package ie.williamswalsh.waits;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExplicitWaitTest {

    private static WebDriver driver;

    // Explicit wait - no implicit wait applied in class.
   private static WebDriverWait wait;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    void explicitWaitForPromoDiscountTest() {
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");

        List<String> productNames = Arrays.asList("Brocolli - 1 Kg", "Cauliflower - 1 Kg", "Cucumber - 1 Kg");
        addProductsToCartByName(productNames);

        driver.findElement(By.xpath("//img[@alt='Cart']")).click();
        driver.findElement(By.xpath("//div[@class='action-block']/button[text()='PROCEED TO CHECKOUT']")).click();
//        Wait for promo code field to load.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='promoCode']")));

//        Once loaded interact with the promo elements.
        driver.findElement(By.xpath("//input[@class='promoCode']")).sendKeys("rahulshettyacademy");
        driver.findElement(By.xpath("//button[@class='promoBtn']")).click();

//        Wait for discount to apply - takes about 3 seconds.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='promoInfo']")));

        String discountPercentage = driver.findElement(By.xpath("//span[@class='discountPerc']")).getText();
        String expectedPercentage = "10%";
        assertEquals(expectedPercentage, discountPercentage);
    }

    void addProductsToCartByName(List<String> productsToAdd) {
        List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));
        for (int i = 0; i < products.size(); i++) {
            if(productsToAdd.contains(products.get(i).getText())) {
                driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
            }
        }
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
