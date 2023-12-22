package ie.williamswalsh.table;

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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableColumnSelectionTest {

    private static WebDriver d;

    private static WebDriverWait wait;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        d = new ChromeDriver();
        wait = new WebDriverWait(d, Duration.ofSeconds(10));
    }

    @Test
    void choosingColumnsTest() throws InterruptedException {
        d.get("https://rahulshettyacademy.com/AutomationPractice/");
        int calculatedSum = 0;
//        Gets third column -> td:nth-child(3)
        for(WebElement e :d.findElements(By.cssSelector(".tableFixHead td:nth-child(4)"))) {
            calculatedSum += Integer.parseInt(e.getText());
        }

        String sumString = d.findElement(By.xpath("//div[@class='totalAmount']")).getText();
        int sum = Integer.parseInt(sumString.split(":")[1].trim());
        assertEquals(sum, calculatedSum);
    }

    @AfterAll
    static void tearDown() {
        d.quit();
    }
}

