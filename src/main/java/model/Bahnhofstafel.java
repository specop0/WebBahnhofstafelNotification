package model;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Model of a bahnhofstafel.
 *
 * @author SpecOp0
 */
public class Bahnhofstafel {

    /**
     * The list of train connections.
     */
    private final List<TrainConnection> trainConnections = new ArrayList<>();

    /**
     * Write the train connections to given file.
     *
     * @param path File path to write to.
     */
    public final void writeToFile(String path) {
        if (!getTrainConnections().isEmpty()) {
            FileOutputStream fos;
            OutputStreamWriter writer;
            BufferedWriter bwriter;
            try {
                fos = new FileOutputStream(path);
                writer = new OutputStreamWriter(fos, "UTF-8");
                bwriter = new BufferedWriter(writer);

                for (TrainConnection player : getTrainConnections()) {
                    bwriter.write(player.toString());
                    bwriter.write(System.lineSeparator());
                }
                bwriter.write(System.lineSeparator());

                bwriter.close();
                writer.close();
                fos.close();
            } catch (IOException ex) {
                // handled
            }
        }
    }

    /**
     * Get the train connections with a special message.
     *
     * @return Train connections with a special message.
     */
    public final List<TrainConnection> getTrainConnectionsWithSpecialMessage() {
        return this.getTrainConnections().stream().filter(
            x -> x.hasSpecialMessage()).collect(Collectors.toList());
    }

    /**
     * Add a train connection.
     *
     * @param trainConnection Train connection to add.
     */
    public final void addTrainConnection(TrainConnection trainConnection) {
        getTrainConnections().add(trainConnection);
    }

    // Getter and Setter
    /**
     * Get all train connections.
     *
     * @return Train connectoins.
     */
    public final List<TrainConnection> getTrainConnections() {
        return trainConnections;
    }

}
