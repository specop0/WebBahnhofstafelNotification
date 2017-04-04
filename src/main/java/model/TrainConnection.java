package model;

import main.Utilities;
import static main.Utilities.isNullOrWhitespace;

/**
 * Model of a train connnection.
 *
 * @author Oliver
 */
public class TrainConnection {

    /**
     * Name of the train connection.
     */
    private final String name;
    /**
     * Time of arrival of the train connection.
     */
    private final String timeOfArrival;
    /**
     * Start station of the train connection.
     */
    private final String startStation;
    /**
     * Arriving stations of the train connections.
     */
    private final String arrivingStations;
    /**
     * Possible special message of the train connection.
     */
    private final String specialMessage;
    /**
     * Date of the train connection
     */
    private final String currentDate;

    /**
     * Initializes a {@code TrainConnection}.
     *
     * @param name Name of the train connection.
     * @param timeOfArrival Time of arrival of the train connection.
     * @param startStation Start station of the train connection.
     * @param arrivingStations Arriving stations of the train connections.
     * @param specialMessage Possible special message of the train connection.
     */
    public TrainConnection(String name, String timeOfArrival, String startStation, String arrivingStations, String specialMessage) {
        this.name = name;
        this.timeOfArrival = timeOfArrival;
        this.startStation = startStation;
        this.arrivingStations = arrivingStations;
        this.specialMessage = specialMessage;
        this.currentDate = Utilities.getCurrentDate();
    }

    /**
     * Initializes a {@code TrainConnection}.
     *
     * @param name Name of the train connection.
     * @param timeOfArrival Time of arrival of the train connection.
     * @param startStation Start station of the train connection.
     * @param arrivingStations Arriving stations of the train connections.
     */
    public TrainConnection(String name, String timeOfArrival, String startStation, String arrivingStations) {
        this(name, timeOfArrival, startStation, arrivingStations, "");
    }

    /**
     * Initializes a {@code TrainConnection}.
     *
     * @param name Name of the train connection.
     * @param timeOfArrival Time of arrival of the train connection.
     * @param startStation Start station of the train connection.
     * @param arrivingStations Arriving stations of the train connections.
     * @param specialMessage Possible special message of the train connection.
     * @param currentDate Date of the train connection
     */
    public TrainConnection(String name, String timeOfArrival, String startStation, String arrivingStations, String specialMessage, String currentDate) {
        this.name = name;
        this.timeOfArrival = timeOfArrival;
        this.startStation = startStation;
        this.arrivingStations = arrivingStations;
        this.specialMessage = specialMessage;
        this.currentDate = currentDate;
    }

    /**
     * Returns a string representation of the train connection.
     *
     * @return String representation of this train connection.
     */
    @Override
    public String toString() {
        return String.format("%s;%s;%s;%s;%s;%s;%s", getName(), getTimeOfArrival(), getCurrentDate(), getStartStation(), getArrivingStations(), getSpecialMessage(), this.hasSpecialMessage());
    }

    /**
     * Parses a string representing a train connection.
     *
     * @param trainConnectionString String representing a train connection.
     * @return Parsed {@code TrainCconnection} or {@code null}.
     */
    public static TrainConnection fromString(String trainConnectionString) {
        TrainConnection trainConnection = null;
        String[] array = trainConnectionString.split(";");
        if (array.length == 7) {
            trainConnection = new TrainConnection(array[0], array[1], array[3], array[4], array[5], array[2]);
        }
        return trainConnection;
    }

    /**
     * Flag indicating if the train connection has a special message.
     *
     * @return
     */
    public boolean hasSpecialMessage() {
        return !isNullOrWhitespace(this.getSpecialMessage());
    }

    /**
     * Compares this train connection to the specified object. The result is {@code
     * true} if and only if the argument is not {@code null} and is a {@code
     * TrainConnection} object that represents the same train connection as this
     * object.
     *
     * @param otherObject The object to compare this {@code TrainConnection}
     * against
     *
     * @return {@code true} if the given object represents a
     * {@code TrainConnection} equivalent to this train connection,
     * {@code false} otherwise
     *
     */
    @Override
    public boolean equals(Object otherObject) {
        if (otherObject.getClass() == this.getClass()) {
            TrainConnection otherConnection = (TrainConnection) otherObject;
            return this.getCurrentDate().equals(otherConnection.getCurrentDate())
                    && this.getName().equals(otherConnection.getName())
                    && this.getTimeOfArrival().equals(otherConnection.getTimeOfArrival())
                    && this.getStartStation().equals(otherConnection.getStartStation())
                    && this.getSpecialMessage().equals(otherConnection.getSpecialMessage());
        }
        return false;
    }

    // getter
    /**
     * Get the name of the train connection.
     *
     * @return Name of the train connection.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the time of arrival of the train connection.
     *
     * @return Time of arrival of the train connection.
     */
    public String getTimeOfArrival() {
        return timeOfArrival;
    }

    /**
     * Get the start station of the train connection.
     *
     * @return Start station of the train connection.
     */
    public String getStartStation() {
        return startStation;
    }

    /**
     * Get the arriving stations of the train connections.
     *
     * @return Arriving stations of the train connections.
     */
    public String getArrivingStations() {
        return arrivingStations;
    }

    /**
     * Get the possible special message of the train connection.
     *
     * @return Possible special message of the train connection.
     */
    public String getSpecialMessage() {
        return specialMessage;
    }

    /**
     * Get the date of the train connection.
     *
     * @return Date of the train connection.
     */
    public String getCurrentDate() {
        return currentDate;
    }

}
