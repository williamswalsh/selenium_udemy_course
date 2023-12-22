package ie.williamswalsh;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BypassSslCertIssue {

    private static WebDriver d;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/drivers/chromedriver-mac-arm64/chromedriver");
        ChromeOptions options = new ChromeOptions();  // Other classes for - FireFoxOptions SafariOptions
        options.setAcceptInsecureCerts(true);

//        can add plugins
//        options.addExtensions()

//        Can add proxy config:
//        Proxy proxy = new Proxy();
//        proxy.setHttpProxy("IP_ADDRER:PORT)
//        options.setCapability("proxy", proxy);

//        Block pop-up windows
//        Like permissions pop-ups etc
        options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));

//        Set download directory of browser:
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", "/directory/path");
        options.setExperimentalOption("prefs", prefs);

//        More: https://chromedriver.chromium.org/capabilities

        d = new ChromeDriver(options);
    }

    @Test
    void bypassSslExceptionTest() {
        d.get("https://expired.badssl.com/");
        assertEquals("expired.badssl.com", d.getTitle());
    }

    @AfterAll
    static void tearDown() {
        d.quit();
    }
}
