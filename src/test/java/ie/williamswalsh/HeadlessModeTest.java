package ie.williamswalsh;

import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class HeadlessModeTest {

    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1440,900)); // Works for headless mode
        // don't think maximise works for headless mode.
    }

    @Test
    void takeScreenShotTest() throws IOException {
        driver.get("https://google.com");

//        Cast driver into TakeScreenshot Class.
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

//        Then move the file to destination folder.
        FileUtils.copyFile(screenshot, new File("/Users/legoman/code/selenium/screenshots/my_3rd_screenshot.png"));
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
