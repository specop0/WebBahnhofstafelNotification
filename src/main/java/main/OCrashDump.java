package main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import static main.Utilities.getTimestamp;

/**
 * Dumps information about an exception or other important information into a
 * logfile with a timestamp.
 *
 * @author Oliver
 */
public class OCrashDump {

    /**
     * The log file to write the information to.
     */
    private PrintWriter logfile = null;

    /**
     * Dumps information about an exception or other important information into
     * a logfile with a timestamp.
     */
    private OCrashDump() {
        String filename = getTimestamp();
        System.out.println(filename);
        try {
            logfile = new PrintWriter(filename);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OCrashDump.class.getName()).log(Level.SEVERE, null, ex);
            logfile = null;
        }
    }

    /**
     * Prints the text to the log file.
     *
     * @param text Text to print.
     */
    public void print(String text) {
        if (null != logfile) {
            logfile.print(text);
        }
    }

    /**
     * Prints the text to the log file with a line break.
     *
     * @param text Text to print.
     */
    public void println(String text) {
        if (null != logfile) {
            logfile.println(text);
        }
    }

    /**
     * Prints the exception to the log file (with line breaks).
     *
     * @param ex Exception to print.
     */
    public void printStackTrace(Exception ex) {
        if (null != logfile) {
            println(ex.toString());
            for (StackTraceElement element : ex.getStackTrace()) {
                println(element.toString());
            }
        }
    }

    /**
     * Closes the log file.
     */
    public void close() {
        if (null != logfile) {
            logfile.close();
            logfile = null;
        }
    }

    /**
     * Dumps the exception to a logfile with the current time stamp.
     *
     * @param message Message at the top of the log.
     * @param ex Exception to print.
     */
    public static void dumpException(String message, Exception ex) {
        OCrashDump crash = new OCrashDump();
        crash.println(message);
        crash.printStackTrace(ex);
        crash.close();
    }

    /**
     * Dumps the exception to a logfile with the current time stamp.
     *
     * @param ex Exception to print.
     */
    public static void dumpException(Exception ex) {
        OCrashDump crash = new OCrashDump();
        crash.printStackTrace(ex);
        crash.close();
    }

    /**
     * Dumps the exception to a logfile with the current time stamp.
     *
     * @param ex Exception to print.
     * @param pageSource Page source of the web driver (string with line
     * breaks).
     */
    public static void dumpPageSource(Exception ex, String pageSource) {
        OCrashDump crash = new OCrashDump();
        crash.printStackTrace(ex);
        String[] buffer = pageSource.split("\n");
        for (String line : buffer) {
            crash.println(line);
        }
        crash.close();
    }

    /**
     * Dumps the exception to a logfile with the current time stamp.
     *
     * @param message Message at the top of the log.
     * @param ex Exception to print.
     * @param pageSource Page source of the web driver (string with line
     * breaks).
     */
    public static void dumpPageSource(String message, Exception ex, String pageSource) {
        OCrashDump crash = new OCrashDump();
        crash.println(message);
        crash.printStackTrace(ex);
        String[] buffer = pageSource.split("\n");
        for (String line : buffer) {
            crash.println(line);
        }
        crash.close();
    }

    /**
     * Dumps the exception to a logfile with the current time stamp.
     *
     * @param message Message at the top of the log.
     * @param pageSource Page source of the web driver (string with line
     * breaks).
     */
    public static void dumpPageSource(String message, String pageSource) {
        OCrashDump crash = new OCrashDump();
        if (!message.equals("")) {
            crash.println(message);
        }
        String[] buffer = pageSource.split("\n");
        for (String line : buffer) {
            crash.println(line);
        }
        crash.close();
    }

}
