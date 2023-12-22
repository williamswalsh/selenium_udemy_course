package ie.williamswalsh.assignments;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckboxTest {

    private static WebDriver d;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        d = new ChromeDriver();
    }

    @Test
    void assignmentTest() {
        d.get("https://rahulshettyacademy.com/AutomationPractice/");

        WebElement parentLabel = d.findElement(By.xpath("//label[@for='benz']"));
        parentLabel.findElement(By.xpath("//input")).click();

        String checkBoxLabel = parentLabel.getText().trim();
        WebElement dropDown = d.findElement(By.id("dropdown-class-example"));
        Select select = new Select(dropDown);
        select.selectByValue(checkBoxLabel.toLowerCase());
        System.out.println(checkBoxLabel);

        d.findElement(By.id("name")).sendKeys(checkBoxLabel);
        d.findElement(By.id("alertbtn")).click();

        d.switchTo().alert().accept();
    }

    @AfterAll
    static void tearDown() {
        d.quit();
    }
}
