package eu.fiveminutes.util;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;

public final class NetworkUtilsImpl implements NetworkUtils {

    private final ConnectivityManager connectivityManager;

    @Inject
    public NetworkUtilsImpl(ConnectivityManager connectivityManager) {
        this.connectivityManager = connectivityManager;
    }

    @Override
    public boolean hasInternetConnection() {
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
