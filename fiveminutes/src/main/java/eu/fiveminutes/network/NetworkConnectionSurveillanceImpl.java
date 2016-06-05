package eu.fiveminutes.network;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final class NetworkConnectionSurveillanceImpl implements NetworkConnectionSurveillance {

    private static final long DELAY_TIME = TimeUnit.SECONDS.toMillis(5L);
    private static final long NO_DELAY = 0L;

    private final ConnectivityInformation connectivityInformation;
    private final InternetAddressResolver internetAddressResolver;
    private final ScheduledExecutorService executorService;

    private final Set<Observer> observers = new HashSet<>();
    private final Runnable connectivityChecker = new ConnectivityChecker();

    private ScheduledFuture scheduledFuture;

    private boolean hasInternetConnection;

    public NetworkConnectionSurveillanceImpl(final ConnectivityInformation connectivityInformation, final InternetAddressResolver internetAddressResolver,
                                             final ScheduledExecutorService executorService) {
        this.connectivityInformation = connectivityInformation;
        this.internetAddressResolver = internetAddressResolver;
        this.executorService = executorService;
    }

    @Override
    public boolean hasInternetConnection() {
        return hasInternetConnection;
    }

    @Override
    public void forceCheck() {
        if (!connectivityInformation.isConnectedToNetwork()) {
            hasInternetConnection = false;
        } else {
            dismissOldFuture();
            scheduleCheck(NO_DELAY);
        }
    }

    private void dismissOldFuture() {
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            scheduledFuture = null;
        }
    }

    @Override
    public void registerObserver(final Observer observer) {
        observers.add(observer);
        observer.connectivityChange(hasInternetConnection());
    }

    @Override
    public void unregisterObserver(final Observer observer) {
        observers.remove(observer);
    }

    private boolean canResolveAddress() {
        return internetAddressResolver.canResolveAddress();
    }

    private void notifyObserverForConnectionChange() {
        for (final Observer observer : observers) {
            observer.connectivityChange(hasInternetConnection());
        }
    }

    private void enqueueDelayed() {
        if (!connectivityInformation.isConnectedToNetwork()) {
            hasInternetConnection = false;
        } else {
            scheduleCheck(DELAY_TIME);
        }
    }

    private void scheduleCheck(final long delay) {
        scheduledFuture = executorService.schedule(connectivityChecker, delay, TimeUnit.SECONDS);
    }

    private final class ConnectivityChecker implements Runnable {

        @Override
        public void run() {
            final boolean oldIsAddressResolvable = hasInternetConnection();
            final boolean newIsAddressResolvable = canResolveAddress();

            hasInternetConnection = newIsAddressResolvable;
            if (oldIsAddressResolvable != newIsAddressResolvable) {
                notifyObserverForConnectionChange();
            }

            enqueueDelayed();
        }
    }
}
