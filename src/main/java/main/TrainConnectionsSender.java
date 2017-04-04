package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import model.Bahnhofstafel;
import model.TrainConnection;

/**
 * Sends (special) information about train connections to a receiver (IFTTT
 * app).
 *
 * @author SpecOp0
 */
public class TrainConnectionsSender {

    /**
     * The filename which logs already pushed train connections, because we only
     * want to sent the information once.
     */
    private final static String ALREADY_PUSHED_TRAIN_CONNECTIONS = "knownTrainConnections.txt";

    /**
     * Send (special) information of the bahnhofstafel to a receiver (IFTTT
     * app).
     *
     * @param bahnhofstafel Bahnhofstafel with the train connections to send.
     * @param triggerUrl URL to post information to (and trigger a push
     * notification).
     */
    public static void sendNotifications(Bahnhofstafel bahnhofstafel, String triggerUrl) {
        List<TrainConnection> alreadyPushedTrainConnections = getAlreadyPushedTrainConnections();

        // notify if we have a train with a special message
        List<TrainConnection> trainConnectionsWithSpecialMessge = bahnhofstafel.getTrainConnectionsWithSpecialMessage();
        if (!trainConnectionsWithSpecialMessge.isEmpty()) {
            trainConnectionsWithSpecialMessge.stream()
                    .filter(x -> !alreadyPushedTrainConnections.contains(x))
                    .forEach(x -> {
                        sendPushNotification(x, triggerUrl);
                        alreadyPushedTrainConnections.add(x);
                    });
        }
        writeAlreadyPushedTrainConnectoins(alreadyPushedTrainConnections);
        // log result
        String filename = Utilities.getTimestamp();
        bahnhofstafel.writeToFile("Parse_" + filename);
    }

    /**
     * Get a list of already pushed train connections (reads them from the log
     * file).
     *
     * @return List of already pushed train connections.
     */
    private static List<TrainConnection> getAlreadyPushedTrainConnections() {
        List<TrainConnection> alreadyPushedTrainConnections = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(ALREADY_PUSHED_TRAIN_CONNECTIONS);
            InputStreamReader reader = new InputStreamReader(fis);
            BufferedReader breader = new BufferedReader(reader);

            String line = breader.readLine();
            while (line != null) {
                TrainConnection connection = TrainConnection.fromString(line);
                if (connection != null) {
                    alreadyPushedTrainConnections.add(connection);
                }
                line = breader.readLine();
            }

            breader.close();
            reader.close();
            fis.close();
        } catch (IOException ex) {
            OCrashDump.dumpException("Error reading already pushed train connections", ex);
        }

        return alreadyPushedTrainConnections;
    }

    /**
     * Writes already pushed train connections to the log file. Will clear the
     * old log file and only write the train connections from today.
     *
     * @param alreadyPushedTrainConnections List of already pushed train
     * connections.
     */
    private static void writeAlreadyPushedTrainConnectoins(List<TrainConnection> alreadyPushedTrainConnections) {
        String currentDate = Utilities.getCurrentDate();
        try {
            FileOutputStream fos = new FileOutputStream(ALREADY_PUSHED_TRAIN_CONNECTIONS);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            BufferedWriter bwriter = new BufferedWriter(writer);

            for (TrainConnection connection : alreadyPushedTrainConnections) {
                if (connection.getCurrentDate().equals(currentDate)) {
                    bwriter.write(connection.toString());
                    bwriter.write(System.lineSeparator());
                }
            }

            bwriter.close();
            writer.close();
            fos.close();
        } catch (IOException ex) {
            OCrashDump.dumpException("Error writing already pushed train connections", ex);
        }
    }

    /**
     * Send (special) information about given train connection.
     *
     * @param trainConnection Train connection to send push message for.
     * @param triggerUrl URL to post information to (and trigger a push
     * notification).
     */
    private static void sendPushNotification(TrainConnection trainConnection, String triggerUrl) {
        try {
            // https://www.mkyong.com/webservices/jax-rs/restfull-java-client-with-java-net-url/
            URL url = new URL(triggerUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            // message
            String input = String.format(
                    "{\"value1\":\"%s\",\"value2\":\"%s\",\"value3\":\"%s\"}",
                    trainConnection.getName(),
                    trainConnection.getTimeOfArrival(),
                    trainConnection.getSpecialMessage());
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != 200) {
                OCrashDump.dumpPageSource(
                        "Failed send Push notification, HTTP error code : " + conn.getResponseCode(),
                        trainConnection.toString());
            }
            // get result
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();
        } catch (IOException e) {
            OCrashDump.dumpException("Failed send Push notification", e);
        }
    }
}
