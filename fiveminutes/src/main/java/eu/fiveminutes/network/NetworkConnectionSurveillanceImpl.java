package eu.fiveminutes.network;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final class NetworkConnectionSurveillanceImpl implements NetworkConnectionSurveillance {

    static final long DELAY_TIME_WITH_INTERNET = TimeUnit.MINUTES.toSeconds(1L);
    static final long DELAY_TIME_NO_INTERNET = TimeUnit.SECONDS.toSeconds(2L);
    static final long NO_DELAY = 0L;

    private final ConnectivityInformation connectivityInformation;
    private final InternetAddressResolver internetAddressResolver;
    private final MainThreadExecutor mainThreadExecutor;
    private final ScheduledExecutorService executorService;

    private final Set<Observer> observers = new HashSet<>();

    private ScheduledFuture scheduledFuture;

    private boolean hasInternetConnection;

    public NetworkConnectionSurveillanceImpl(final ConnectivityInformation connectivityInformation, final InternetAddressResolver internetAddressResolver,
                                             final MainThreadExecutor mainThreadExecutor, final ScheduledExecutorService executorService) {
        this.connectivityInformation = connectivityInformation;
        this.internetAddressResolver = internetAddressResolver;
        this.mainThreadExecutor = mainThreadExecutor;
        this.executorService = executorService;
        executeNow();
    }

    @Override
    public boolean hasInternetConnection() {
        return hasInternetConnection;
    }

    @Override
    public void forceCheck() {
        dismissOldFuture();
        executeNow();
    }

    private void executeNow() {
        executorService.execute(new ConnectivityChecker());
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
        return connectivityInformation.isConnectedToNetwork() && internetAddressResolver.canResolveAddress();
    }

    private void notifyObserverForConnectionChange() {
        mainThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                for (final Observer observer : observers) {
                    observer.connectivityChange(hasInternetConnection());
                }
            }
        });
    }

    private void enqueueDelayed() {
        if (!hasInternetConnection()) {
            scheduleCheck(DELAY_TIME_NO_INTERNET);
        } else {
            scheduleCheck(DELAY_TIME_WITH_INTERNET);
        }
    }

    private void scheduleCheck(final long delay) {
        scheduledFuture = executorService.schedule(new ConnectivityChecker(), delay, TimeUnit.SECONDS);
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
