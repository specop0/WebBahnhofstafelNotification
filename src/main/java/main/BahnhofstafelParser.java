package main;

import webdriver.IWebDriver;
import model.Bahnhofstafel;
import model.TrainConnection;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Parser of a bahnhofstafel from the web.
 *
 * @author Oliver
 */
public class BahnhofstafelParser {

    /**
     * Parse the bahnhofstafel.
     *
     * @param webDriver Web Driver to use.
     * @param url URL of the bahnhofstafel.
     * @return The parsed bahnhofstafel
     */
    public static Bahnhofstafel ParseBahnhofstafel(IWebDriver webDriver, String url) {
        webDriver.loadPage(url);
        Bahnhofstafel bahnhofstafel = new Bahnhofstafel();
        FillBahnhofstafelWithTrainConnections(webDriver, bahnhofstafel);
        return bahnhofstafel;
    }

    /**
     * Fills the bahnhofstafel with train connections.
     *
     * @param webDriver Web driver with the page of the bahnhofstafel loaded.
     * @param bahnhofstafel Bahnhofstafel to fill.
     */
    private static void FillBahnhofstafelWithTrainConnections(IWebDriver webDriver, Bahnhofstafel bahnhofstafel) {
        try {
            // the train connections fill be filled after some time -> wait 2s
            Thread.sleep(2000);

            WebElement table = webDriver.findElement(By.tagName("table")).findElement(By.tagName("tbody"));
            List<WebElement> rows = table.findElements(IWebDriver.CHILD_NODES);
            for (WebElement row : rows) {
                TrainConnection trainConnection = ParseTrainConnection(row);
                if (trainConnection != null) {
                    bahnhofstafel.addTrainConnection(trainConnection);
                } else {
                    System.out.println(rows.indexOf(row) + ": " + row.findElement(IWebDriver.CHILD_NODES).getText());
                }
            }
        } catch (Exception ex) {
            OCrashDump.dumpException(ex);
            System.out.println("Exception " + ex.getClass().toString() + " occured");
        }
    }

    /**
     * Parse a train connectoin from given web element.
     *
     * @param trainConnectionRow Web Element which represens a train connection.
     * @return
     */
    private static TrainConnection ParseTrainConnection(WebElement trainConnectionRow) {
        TrainConnection trainConnection = null;
        try {
            String name = null;
            String timeOfArrival = null;
            String startStation = null;
            String arrivingStations = null;
            String specialMessage = null;

            List<WebElement> columns = trainConnectionRow.findElements(IWebDriver.CHILD_NODES);

            // first column
            List<WebElement> timeAndName = columns.get(0).findElements(IWebDriver.CHILD_NODES);
            timeOfArrival = timeAndName.get(0).getText();
            name = sanitizeString(timeAndName.get(1).getText());

            // second column
            startStation = sanitizeString(columns.get(1).findElement(IWebDriver.CHILD_NODES).getText());

            // fourth column
            List<WebElement> stationsAndMessage = columns.get(3).findElements(IWebDriver.CHILD_NODES);
            arrivingStations = sanitizeString(stationsAndMessage.get(0).getText());
            specialMessage = sanitizeString(stationsAndMessage.get(1).getText());

            trainConnection = new TrainConnection(name, timeOfArrival, startStation, arrivingStations, specialMessage);
        } catch (Exception ex) {
            OCrashDump.dumpException(ex);
            System.out.println("Exception " + ex.getClass().toString() + " occured");
        }
        return trainConnection;
    }

    /**
     * Sanitizes the given string.
     *
     * @param input String to sanitize.
     * @return Sanitized string.
     */
    private static String sanitizeString(String input) {
        String sanitizedString = input.replaceAll("ä", "ae");
        sanitizedString = sanitizedString.replaceAll("ö", "oe");
        sanitizedString = sanitizedString.replaceAll("ü", "ue");
        return sanitizedString;
    }

}
