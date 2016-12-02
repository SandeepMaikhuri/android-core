package eu.fiveminutes.network;

public interface NetworkConnectionSurveillance {

    boolean hasInternetConnection();

    void forceCheck();

    void registerObserver(Observer observer);

    void unregisterObserver(Observer observer);

    interface Observer {

        void connectivityChange(boolean connected);
    }
}
