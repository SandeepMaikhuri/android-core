package eu.fiveminutes.network;

/**
 * Interface for providing information whether device is connected to some network.
 * NOTE: Information provided here doesn't mean that device has internet connection.
 */
public interface ConnectivityInformation {

    /**
     * Provides information if device is connected to some network.
     * @return true if connected, false otherwise.
     */
    boolean isConnectedToNetwork();
}
