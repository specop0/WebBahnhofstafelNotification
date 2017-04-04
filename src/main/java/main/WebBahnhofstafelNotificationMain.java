package main;

import webdriver.IWebDriver;
import webdriver.WebDriverGhost;
import model.Bahnhofstafel;
import java.io.FileNotFoundException;
import org.openqa.selenium.remote.UnreachableBrowserException;

/**
 *
 * @author Oliver
 */
public class WebBahnhofstafelNotificationMain {

    /**
     * The web driver to use.
     */
    private static IWebDriver driver;

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 2) {
            System.out.println("Wrong usage: *.jar {URL of WebBahnhofstafel} {URL of IFTTT trigger}");
            return;
        }
        IWebDriver webDriver = WebDriverGhost.GetNewWebDriver();

        String webBahnhofstafelUrl = args[0];
        Bahnhofstafel bahnhofstafel = BahnhofstafelParser.ParseBahnhofstafel(webDriver, webBahnhofstafelUrl);

        String triggerUrl = args[1];
        TrainConnectionsSender.sendNotifications(bahnhofstafel, triggerUrl);

        try {
            webDriver.quit();
        } catch (UnreachableBrowserException ex) {
            // handled
        }
    }

    /**
     * @return the driver
     */
    public static IWebDriver getDriver() {
        return driver;
    }

    /**
     * @param aDriver the driver to set
     */
    public static void setDriver(IWebDriver aDriver) {
        driver = aDriver;
    }

}
