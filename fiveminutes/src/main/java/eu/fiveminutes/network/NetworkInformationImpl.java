package eu.fiveminutes.network;

public final class NetworkInformationImpl implements NetworkInformation {

    private final NetworkConnectionSurveillance networkConnectionSurveillance;

    public NetworkInformationImpl(final NetworkConnectionSurveillance networkConnectionSurveillance) {
        this.networkConnectionSurveillance = networkConnectionSurveillance;
    }

    @Override
    public boolean hasInternetConnection() {
        return networkConnectionSurveillance.hasInternetConnection();
    }
}
