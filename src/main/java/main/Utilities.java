package main;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Utilitiy functions.
 *
 * @author Oliver
 */
public class Utilities {

    /**
     * Checks if the string is null or has only whitespace.
     *
     * @param string String to check.
     * @return True if the string is null or has only whitespace.
     */
    public static boolean isNullOrWhitespace(String string) {
        return string == null || isWhitespace(string);

    }

    /**
     * Checks if the string has only whitespace.
     *
     * @param string String to check.
     * @return True if the string has only whitespace.
     */
    private static boolean isWhitespace(String string) {
        // http://stackoverflow.com/questions/8476588/java-equivalent-of-c-sharp-string-isnullorempty-and-string-isnullorwhitespace
        int length = string.length();
        if (length > 0) {
            for (int start = 0, middle = length / 2, end = length - 1; start <= middle; start++, end--) {
                if (string.charAt(start) > ' ' || string.charAt(end) > ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Get the current time stamp with the .txt file extension.
     *
     * @return The filename with the current time stamp.
     */
    public static String getTimestamp() {
        Calendar date = Calendar.getInstance();
        Timestamp timestamp = new Timestamp(date.getTime().getTime());
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd_HH-mm-ss_SSS");
        String filename = dateFormat.format(timestamp) + ".txt";
        return filename;
    }

    /**
     * Get the current date.
     *
     * @return Current date.
     */
    public static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        Timestamp timestamp = new Timestamp(calendar.getTime().getTime());
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String currentDate = dateFormat.format(timestamp);
        return currentDate;
    }
}
