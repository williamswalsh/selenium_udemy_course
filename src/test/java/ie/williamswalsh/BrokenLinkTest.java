package ie.williamswalsh;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.FileSystemException;
import java.util.List;

import static io.opentelemetry.semconv.trace.attributes.SemanticAttributes.DbCosmosdbOperationTypeValues.HEAD;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class BrokenLinkTest {

    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
    }

//    Broken link -> Bad url
    @Test
    void brokenLinkErrorStatusCodeTest() throws IOException {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        WebElement badLink = driver.findElement(By.xpath("//a[@href='https://rahulshettyacademy.com/brokenlink']"));


        String url = badLink.getAttribute("href");
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("HEAD");
        conn.connect();
        int respCode = conn.getResponseCode();
        if( respCode > 399) {
            System.out.println("BAD URL: " + url);
        }
    }

    @Test
    void getAllLinksResponseCodes() throws IOException {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        List<WebElement> links = driver.findElements(By.cssSelector("li[class='gf-li'] a"));

        for (WebElement link :links) {
            String url = link.getAttribute("href");
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("HEAD");
            conn.connect();
            int respCode = conn.getResponseCode();

            System.out.println("CODE: " + respCode + ", URL:  " + url);

//            Can use soft assertions as part of assertJ to skip a bad url and keep iterating then report problem at end.
//            if( respCode > 399) {
//                System.out.println("BAD URL: " + url);
//                fail();
//            }
        }
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
