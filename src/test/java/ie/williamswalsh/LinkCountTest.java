package ie.williamswalsh;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class LinkCountTest {

    private static WebDriver d;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        d = new ChromeDriver();
    }

    @Test
    void getCountOfAnchorTagsTests() {
        d.get("https://rahulshettyacademy.com/AutomationPractice/");
        // get count of links
        int linkCount = d.findElements(By.xpath("//a")).size();
        System.out.println(linkCount);
    }

    @Test
    void getCountOfAnchorTagsInFooterTests() {
        d.get("https://rahulshettyacademy.com/AutomationPractice/");

        int linkCount = d.findElement(By.xpath("//div[@id='gf-BIG']")).findElements(By.tagName("a")).size();
        System.out.println(linkCount);
    }

    @Test
    void getCountInFooterFirstColumnTests() {
        d.get("https://rahulshettyacademy.com/AutomationPractice/");

        int linkCount = d.findElement(By.xpath("//div[@id='gf-BIG']/table[@class='gf-t']/tbody/tr[1]/td[1]")).findElements(By.tagName("a")).size();
        System.out.println(linkCount);
    }

//    Open page in tabs.
    @Test
    void clickLinksInFooterFirstColumnTests() throws InterruptedException {
        d.get("https://rahulshettyacademy.com/AutomationPractice/");

        List<WebElement> links = d.findElement(By.xpath("//div[@id='gf-BIG']/table[@class='gf-t']/tbody/tr[1]/td[1]")).findElements(By.tagName("a"));
        for(int i=1; i<links.size(); i++) {
//           ***** NB: doing a control+enter will open the links in new tabs.
            String clickOnLinkTab = Keys.chord(Keys.COMMAND, Keys.ENTER);   // NB: Keys are OS SPECIFIC!!!!!! NB CTRL in windows/linux

//            Open each link in a new window using key combination.
            links.get(i).sendKeys(clickOnLinkTab);
        }

//        Wait for all pages to load
        Thread.sleep(5000);

//        Iterate through windowHandles to get title of each page.
        Set<String> windowHandles = d.getWindowHandles();
        for (String windowHandle : windowHandles) {
            d.switchTo().window(windowHandle);
            System.out.println(d.getTitle());
        }
    }

    @AfterAll
    static void tearDown() {
        d.quit();
    }
}
