package ie.williamswalsh.assignments;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VegetableShopTest {

    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.of(ChronoUnit.SECONDS));
    }

    /**
     * This test gets all products on page.
     * Iterates through them looking for cucumber product.
     * When it finds cucumber it uses the same index to click the add to cart button.
     */
    @Test
    void addMultipleProductsToBasketTest() throws InterruptedException {
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
        Thread.sleep(3000);
        List<String> productNames = Arrays.asList("Brocolli - 1 Kg", "Cauliflower - 1 Kg", "Cucumber - 1 Kg");
        addProductsToCartByName(productNames);
//        Thread.sleep(500);

        String itemCount = driver.findElement(By.xpath("//div[@class='cart-info']/table/tbody/tr[1]/td[3]/strong")).getText();
        assertEquals("" + productNames.size(), itemCount);

        String totalPrice = driver.findElement(By.xpath("//div[@class='cart-info']/table/tbody/tr[2]/td[3]/strong")).getText();
        assertEquals("228", totalPrice);
// Possibly need to wait here.
        driver.findElement(By.xpath("//img[@alt='Cart']")).click();
        driver.findElement(By.xpath("//div[@class='action-block']/button[text()='PROCEED TO CHECKOUT']")).click();
//        OR
//        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/cart");
        driver.findElement(By.xpath("//input[@class='promoCode']")).sendKeys("rahulshettyacademy");
        driver.findElement(By.xpath("//button[@class='promoBtn']")).click();

//        Wait for discount to apply
        Thread.sleep(8000);
        String discountPercentage = driver.findElement(By.xpath("//span[@class='discountPerc']")).getText();
        String expectedPercentage = "10%";
        assertEquals(expectedPercentage, discountPercentage);

//        Check discount has applied
        String discountedAmt = driver.findElement(By.xpath("//span[@class='discountAmt']")).getText();
        String expectedAmt = "205.2";
        assertEquals(expectedAmt, discountedAmt);
    }

    // First iteration ok
    // Second iteration off by one
    // Third iteration off by two??
    // text locator was source of issue -> "Add to cart" text changes once the button is clicked modifying the list of "add to cart" buttons.
    // which affected the index.
    void addProductsToCartByName(List<String> productsToAdd) {
        List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));
        for (int i = 0; i < products.size(); i++) {
            if(productsToAdd.contains(products.get(i).getText())) {
//                driver.findElements(By.xpath("//button[text()='ADD TO CART']")).get(i).click();    // Causes Bug.
                driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
            }
        }
    }
//    @AfterAll
//    static void tearDown() {
//        driver.quit();
//    }
}
