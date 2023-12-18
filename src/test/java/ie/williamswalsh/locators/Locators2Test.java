package ie.williamswalsh.locators;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Locators2Test {

    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    void siblingNavigation() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
//        Absolute xpath starts with single forward slash "/" - "/html/body/header"
//        Relative xpath - "//" - "//header/h1"
        WebElement practiceButton = driver.findElement(By.xpath("//header/div/button[text()='Practice']"));
        WebElement loginButton = driver.findElement(By.xpath("//header/div/button[text()='Practice']/following-sibling::button[1]"));
        driver.quit();
    }
}
