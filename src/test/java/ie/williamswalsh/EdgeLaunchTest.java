package ie.williamswalsh;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeLaunchTest {

    private static WebDriver driver;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.edge.driver", "/Users/legoman/code/selenium/drivers/edge_driver_m1_mac");
        driver = new EdgeDriver();
    }

    @Test
    void getUrlEndpoint() {
        driver.get("https://rahulshettyacademy.com");
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
