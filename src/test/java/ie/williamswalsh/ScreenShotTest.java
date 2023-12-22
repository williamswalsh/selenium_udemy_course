package ie.williamswalsh;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScreenShotTest {

    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    void takeScreenShotTest() throws IOException {
        driver.get("https://google.com");

//        Cast driver into TakeScreenshot Class.
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

//        Then move the file to destination folder.
        FileUtils.copyFile(screenshot, new File("/Users/legoman/code/selenium/screenshots/my_1st_screenshot.png"));
    }

    @Test
    void takeScreenShotThatWillBeDeniedTest() {
        driver.get("https://google.com");

//        Cast driver into TakeScreenshot Class.
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);


        assertThrows(FileSystemException.class,
                () -> {
                    //        Then move the file to destination folder - root directory.
                    FileUtils.copyFile(screenshot, new File("/my_1st_screenshot.png"));
                });
    }

    @Test
    void takeScreenShotOfElementTest() throws IOException {
        driver.get("https://google.com");
        driver.findElement(By.xpath("//div[@class='QS5gu sy4vM']")).click();

        WebElement googleLogoImage = driver.findElement(By.xpath("//img[@class='lnXdpd']"));
        File screenshot = googleLogoImage.getScreenshotAs(OutputType.FILE);

//        Then move the file to destination folder.
        FileUtils.copyFile(screenshot, new File("/Users/legoman/code/selenium/screenshots/my_2nd_screenshot.png"));
    }

    @Test
    void getSizeOfElementTest() throws IOException {
        driver.get("https://google.com");
        driver.findElement(By.xpath("//div[@class='QS5gu sy4vM']")).click();

        WebElement googleLogoImage = driver.findElement(By.xpath("//img[@class='lnXdpd']"));

//        Get width & height(dimensions) of element
        int h = googleLogoImage.getRect().getDimension().getHeight();
        int w = googleLogoImage.getRect().getDimension().getWidth();
        System.out.println("Height: " + h);
        System.out.println("Width: " + w);
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
