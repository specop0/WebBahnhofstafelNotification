package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.UnreachableBrowserException;

/**
 * Interface for additional functions of the WebDriver.
 *
 * @author SpecOp0
 */
public interface IWebDriver extends WebDriver {

    /**
     * Load the given page. Must handle all relevant exceptions.
     *
     * @param url URL to load.
     */
    void loadPage(String url);

    /**
     * By statement to get the child nodes.
     */
    By CHILD_NODES = By.xpath("./*");
    /**
     * By statement to get the parent element.
     */
    By PARENT_ELEMENT = By.xpath("..");

    /**
     * Get the parent element of given child.
     *
     * @param child child to get parent for.
     * @return Parent of the child.
     */
    static WebElement parentElement(WebElement child) {
        return child.findElement(PARENT_ELEMENT);
    }

    /**
     * Load the given page. Must handle all relevant exceptions.
     *
     * @param webDriver WebDriver to use.
     * @param url URL to load.
     */
    static void loadPage(IWebDriver webDriver, String url) {
        try {
            webDriver.get(url);
        } catch (TimeoutException ex) {
            System.out.println("timeout");
        } catch (UnhandledAlertException ex) {
            System.out.println("alert");
        } catch (UnreachableBrowserException | NoSuchWindowException ex) {
            System.out.println("unreachable or no such window");
        } catch (WebDriverException ex) {
            System.out.println("web driver exception");
            System.out.println(ex.toString());
            for (StackTraceElement element : ex.getStackTrace()) {
                System.out.println(element.toString());
            }
        }
    }

}
