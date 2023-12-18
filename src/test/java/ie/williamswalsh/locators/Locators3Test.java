package ie.williamswalsh.locators;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Locators3Test {

    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    void childToParent() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
//        Parent - //header/div/button[1]/parent::div
        String downloadDocText = driver.findElement(By.xpath("//header/div/button[text()='Practice']/parent::div/parent::header/a[@href='https://rahulshettyacademy.com/documents-request']")).getText();
        assertEquals("Free Access to InterviewQues/ResumeAssistance/Material", downloadDocText);
        driver.quit();
    }
}
