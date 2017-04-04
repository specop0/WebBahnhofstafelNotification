package webdriver;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Chrome/Chromium Web Driver.
 *
 * @author SpecOp0
 */
public class WebDriverChrome extends ChromeDriver implements IWebDriver {

    /**
     * Default path of the chrome binaries.
     */
    private static final String CHROME_BINARY_PATH
        = "/usr/lib/chromium-browser/chromium-browser";
    //= "P:\\Google\\Chrome\\Application\\chrome.exe";

    /**
     * Default path of the chrome driver.
     */
    private static final String CHROME_DRIVER_PATH = "chromedriver";

    /**
     * Chrome/Chromium Web Driver.
     */
    private WebDriverChrome(ChromeDriverService chromeService, ChromeOptions chromeOptions) {
        super(chromeService, chromeOptions);
        manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
        manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    /**
     * Get a new web driver.
     *
     * @return Created web driver.
     */
    public static WebDriverChrome GetNewWebDriver() {
        return GetNewWebDriver(CHROME_BINARY_PATH, CHROME_DRIVER_PATH);
    }

    /**
     * Get a new web driver.
     *
     * @param binaryPath Path of the chrome binary.
     * @return Created web driver.
     */
    public static WebDriverChrome GetNewWebDriver(String binaryPath) {
        return GetNewWebDriver(binaryPath, CHROME_DRIVER_PATH);
    }

    /**
     * Get a new web driver.
     *
     * @param binaryPath Path of the chrome binary.
     * @param chromeDriverPath Path of the chrome driver.
     * @return Created web driver.
     */
    public static WebDriverChrome GetNewWebDriver(String binaryPath, String chromeDriverPath) {
        WebDriverChrome webDriver = null;
        try {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setBinary(binaryPath);
            File chromeDriver = new File(chromeDriverPath);
            ChromeDriverService chromeService = new ChromeDriverService.Builder()
                    .usingDriverExecutable(chromeDriver)
                    .usingAnyFreePort()
                    .build();
            webDriver = new WebDriverChrome(chromeService, chromeOptions);
            System.out.println("WebDriver started successfully!");
        } catch (Exception ex) {
            System.out.println("WebDriver start failed");
        }
        return webDriver;
    }

    /**
     * Load the given page. Must handle all relevant exceptions.
     *
     * @param url URL to load.
     */
    @Override
    public void loadPage(String url) {
        IWebDriver.loadPage(this, url);
    }

}
