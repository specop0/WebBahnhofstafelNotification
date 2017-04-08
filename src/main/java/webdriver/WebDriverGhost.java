package webdriver;

import main.OCrashDump;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Ghost Web Driver.
 *
 * @author SpecOp0
 */
public class WebDriverGhost extends PhantomJSDriver implements IWebDriver {

    /**
     * Ghost Web Driver.
     */
    private WebDriverGhost(Capabilities capabilities) {
        super(capabilities);
    }

    /**
     * Get a new web driver.
     *
     * @return Created web driver.
     */
    public static IWebDriver GetNewWebDriver() {
        IWebDriver webDriver = null;
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setJavascriptEnabled(true);
            capabilities.setCapability(
                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, 
                "/usr/local/bin/phantomjs");
            webDriver = new WebDriverGhost(capabilities);

            System.out.println("WebDriver started successfully!");
        } catch (Exception ex) {
            OCrashDump.dumpException(ex);
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
