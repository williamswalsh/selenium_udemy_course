package ie.williamswalsh.waits;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FluentWaitTest {

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");

    }

    @Test
    void fluentWait() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        FluentWait wait = new FluentWait<>(driver);
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@id='start']/button")).click();
//        Wait until hello world text is displayed.
//        Can also be done with WebDriverWait.
        wait.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class);

//        Wait until element is displayed.
//        Fluent wait forces you to define the detection methods.
        WebElement helloWorldElem =
                (WebElement) wait.until((Function<WebDriver, WebElement>) driver1 ->
                {
                    if (driver1.findElement(By.cssSelector("[id='finish'] h4")).isDisplayed()) {
                        return driver1.findElement(By.cssSelector("[id='finish'] h4"));
                    }
                    return null;
                });
        assertEquals("Hello World!", helloWorldElem.getText());

        driver.quit();
    }
}
