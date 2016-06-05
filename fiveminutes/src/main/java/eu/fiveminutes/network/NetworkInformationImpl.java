package eu.fiveminutes.network;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public final class NetworkInformationImpl implements NetworkInformation {

    private final ConnectivityManager connectivityManager;
    private final NetworkConnectionSurveillance networkConnectionSurveillance;

    public NetworkInformationImpl(final ConnectivityManager connectivityManager, final NetworkConnectionSurveillance networkConnectionSurveillance) {
        this.connectivityManager = connectivityManager;
        this.networkConnectionSurveillance = networkConnectionSurveillance;
    }

    @Override
    public boolean hasInternetConnection() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isConnectedToNetwork() {
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
