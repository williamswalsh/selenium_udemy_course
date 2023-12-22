package ie.williamswalsh.assignments;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableAssignmentTest {

    private static WebDriver d;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        d = new ChromeDriver();
    }

    @Test
    void actionTests() {
        d.get("https://rahulshettyacademy.com/AutomationPractice/");
        WebElement coursesTable = d.findElement(By.xpath("//table[@name='courses']"));
        List<WebElement> rows = coursesTable.findElements(By.tagName("tr"));
        int numOfRows = rows.size();

        List<WebElement> columns = rows.get(2).findElements(By.tagName("td"));
        int numOfColumns = columns.size();

        System.out.println("Rows:    " + numOfRows);
        System.out.println("Columns: " + numOfColumns);

        columns.forEach((e) -> System.out.println(e.getText()));
    }

    @AfterAll
    static void tearDown() {
        d.quit();
    }
}
