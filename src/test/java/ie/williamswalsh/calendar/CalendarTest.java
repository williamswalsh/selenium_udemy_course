package ie.williamswalsh.calendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CalendarTest {

    private static WebDriver d;

    private static WebDriverWait wait;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        d = new ChromeDriver();
        wait = new WebDriverWait(d, Duration.ofSeconds(10));
    }

    @Test
    void calendarTest() throws InterruptedException {
        d.get("https://path2usa.com/travel-companion/");
        Thread.sleep(1000);
        WebElement calendar = d.findElement(By.xpath("//input[@id='form-field-travel_comp_date']"));
        System.out.println(calendar.getText());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='form-field-travel_comp_date']")));
        calendar.click();

//        Bug with example - not sure of solution:
//        org.openqa.selenium.ElementClickInterceptedException: element click intercepted: Element is not clickable at point (199, 1416)

//        Every day has this signature: "//td[@class='day']"
//        List<WebElement> dates = d.findElements(By.xpath("//td[@class='day']"));
    }



//    @AfterAll
//    static void tearDown() {
//        d.quit();
//    }
}
