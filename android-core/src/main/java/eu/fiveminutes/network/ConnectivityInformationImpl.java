package eu.fiveminutes.network;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public final class ConnectivityInformationImpl implements ConnectivityInformation {

    private final ConnectivityManager connectivityManager;

    public ConnectivityInformationImpl(final ConnectivityManager connectivityManager) {
        this.connectivityManager = connectivityManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isConnectedToNetwork() {
        final NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
