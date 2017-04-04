package webdriver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Firefox Web Driver.
 *
 * @author Oliver
 */
public class WebDriverFF extends FirefoxDriver implements IWebDriver {

    /**
     * Default path of the geckodriver.
     */
    private static final String FIREFOX_DRIVER_PATH
        = "/home/oliver/Documents/WebBahnhofstafel/geckodriver";
    // = "C:\\Users\\Oliver\\Documents\\NetBeansProjects\\WebBahnhofstafelNotification\\geckodriver.exe";

    /**
     * Firefox Web Driver.
     */
    private WebDriverFF() {
        super();
        manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
        manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    /**
     * Get a new web driver.
     *
     * @return Created web driver.
     */
    public static WebDriverFF GetNewWebDriver() {
        return GetNewWebDriver(FIREFOX_DRIVER_PATH);
    }

    /**
     * Get a new web driver.
     *
     * @param driverPath Path of the geckodriver.
     * @return Created web driver.
     */
    public static WebDriverFF GetNewWebDriver(String driverPath) {
        WebDriverFF webDriver = null;
        try {
            System.setProperty("webdriver.gecko.driver", driverPath);
            webDriver = new WebDriverFF();
            System.out.println("WebDriver started successfully!");
        } catch (Exception ex) {
            ex.printStackTrace();
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
