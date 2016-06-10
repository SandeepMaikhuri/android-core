package eu.fiveminutes.network;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class NetworkConnectionSurveillanceTest {

    private ConnectivityInformation mockConnectivityInformation;
    private InternetAddressResolver mockInternetAddressResolver;
    private NetworkConnectionSurveillance networkConnectionSurveillance;

    private ScheduledExecutorService mockScheduledExecutorService;
    private StubScheduledExecutorService stubScheduledExecutorService;
    private StubMainThreadExecutor stubMainThreadExecutor;

    private NetworkConnectionSurveillance.Observer observer = Mockito.mock(NetworkConnectionSurveillance.Observer.class);

    @Before
    public void setUp() throws Exception {
        mockConnectivityInformation = Mockito.mock(ConnectivityInformation.class);
        mockInternetAddressResolver = Mockito.mock(InternetAddressResolver.class);
        mockScheduledExecutorService = Mockito.mock(ScheduledExecutorService.class);
        stubScheduledExecutorService = new StubScheduledExecutorService(mockScheduledExecutorService);
        stubMainThreadExecutor = new StubMainThreadExecutor();
        networkConnectionSurveillance = new NetworkConnectionSurveillanceImpl(mockConnectivityInformation, mockInternetAddressResolver, stubMainThreadExecutor,
                                                                              stubScheduledExecutorService);
    }

    @Test
    public void testNoInternetConnectionIfNotConnectedToNetwork() {
        Mockito.when(mockConnectivityInformation.isConnectedToNetwork()).thenReturn(false);
        Mockito.when(mockInternetAddressResolver.canResolveAddress()).thenReturn(false);
        stubScheduledExecutorService.executeNext();

        Assert.assertFalse(networkConnectionSurveillance.hasInternetConnection());
    }

    @Test
    public void testNoInternetConnectionIfConnectedToNetworkButNoInternet() {
        Mockito.when(mockConnectivityInformation.isConnectedToNetwork()).thenReturn(true);
        Mockito.when(mockInternetAddressResolver.canResolveAddress()).thenReturn(false);
        stubScheduledExecutorService.executeNext();

        Assert.assertFalse(networkConnectionSurveillance.hasInternetConnection());
    }

    @Test
    public void testNoInternetConnectionIfConnectedToNetworkAndHasInternet() {
        Mockito.when(mockConnectivityInformation.isConnectedToNetwork()).thenReturn(true);
        Mockito.when(mockInternetAddressResolver.canResolveAddress()).thenReturn(true);
        stubScheduledExecutorService.executeNext();

        Assert.assertTrue(networkConnectionSurveillance.hasInternetConnection());
    }

    @Test
    public void testObserverNotifiedWhenSubscribedNoInternet() {
        Mockito.when(mockConnectivityInformation.isConnectedToNetwork()).thenReturn(true);
        Mockito.when(mockInternetAddressResolver.canResolveAddress()).thenReturn(false);

        stubScheduledExecutorService.executeNext();
        networkConnectionSurveillance.registerObserver(observer);

        Mockito.verify(observer).connectivityChange(false);
    }

    @Test
    public void testObserverNotifiedWhenSubscribedWithInternet() {
        Mockito.when(mockConnectivityInformation.isConnectedToNetwork()).thenReturn(true);
        Mockito.when(mockInternetAddressResolver.canResolveAddress()).thenReturn(true);

        stubScheduledExecutorService.executeNext();
        networkConnectionSurveillance.registerObserver(observer);

        Mockito.verify(observer).connectivityChange(true);
    }

    @Test
    public void testObserverNotNotifiedWhenNoConnectionChange() {
        Mockito.when(mockConnectivityInformation.isConnectedToNetwork()).thenReturn(true);
        Mockito.when(mockInternetAddressResolver.canResolveAddress()).thenReturn(true);

        stubScheduledExecutorService.executeNext();
        networkConnectionSurveillance.registerObserver(observer);

        stubScheduledExecutorService.executeNext();
        stubScheduledExecutorService.executeNext();
        stubScheduledExecutorService.executeNext();

        Mockito.verify(observer, Mockito.times(1)).connectivityChange(true);
    }

    @Test
    public void testObserverNotNotifiedWhenConnectionChangeFromConnectedToNotConnected() {
        Mockito.when(mockConnectivityInformation.isConnectedToNetwork()).thenReturn(true);
        Mockito.when(mockInternetAddressResolver.canResolveAddress()).thenReturn(true);

        stubScheduledExecutorService.executeNext();
        networkConnectionSurveillance.registerObserver(observer);

        Mockito.when(mockInternetAddressResolver.canResolveAddress()).thenReturn(false);
        stubScheduledExecutorService.executeNext();

        Mockito.verify(observer, Mockito.times(1)).connectivityChange(true);
        Mockito.verify(observer, Mockito.times(1)).connectivityChange(false);
    }

    @Test
    public void testObserverNotNotifiedWhenConnectionChangeFromNotConnectedToConnected() {
        Mockito.when(mockConnectivityInformation.isConnectedToNetwork()).thenReturn(true);
        Mockito.when(mockInternetAddressResolver.canResolveAddress()).thenReturn(false);

        stubScheduledExecutorService.executeNext();
        networkConnectionSurveillance.registerObserver(observer);

        Mockito.when(mockInternetAddressResolver.canResolveAddress()).thenReturn(true);
        stubScheduledExecutorService.executeNext();

        Mockito.verify(observer, Mockito.times(1)).connectivityChange(true);
        Mockito.verify(observer, Mockito.times(1)).connectivityChange(false);
    }

    @Test
    public void testUnregisterObserver() {
        Mockito.when(mockConnectivityInformation.isConnectedToNetwork()).thenReturn(true);
        Mockito.when(mockInternetAddressResolver.canResolveAddress()).thenReturn(true);

        stubScheduledExecutorService.executeNext();
        networkConnectionSurveillance.registerObserver(observer);
        networkConnectionSurveillance.unregisterObserver(observer);

        Mockito.when(mockInternetAddressResolver.canResolveAddress()).thenReturn(false);
        stubScheduledExecutorService.executeNext();

        Mockito.verify(observer, Mockito.times(1)).connectivityChange(true);
    }

    @Test
    public void testSchedulingOnObjectCreation() {
        Mockito.verify(mockScheduledExecutorService, Mockito.times(1))
               .execute(Mockito.any(Runnable.class));

        stubScheduledExecutorService.executeNext();

        Mockito.verify(mockScheduledExecutorService, Mockito.times(2))
               .schedule(Mockito.any(Runnable.class), Mockito.eq(NetworkConnectionSurveillanceImpl.DELAY_TIME_NO_INTERNET), Mockito.eq(TimeUnit.SECONDS));

        Mockito.when(mockConnectivityInformation.isConnectedToNetwork()).thenReturn(true);
        Mockito.when(mockInternetAddressResolver.canResolveAddress()).thenReturn(true);

        stubScheduledExecutorService.executeNext();

        Mockito.verify(mockScheduledExecutorService, Mockito.times(1))
               .schedule(Mockito.any(Runnable.class), Mockito.eq(NetworkConnectionSurveillanceImpl.DELAY_TIME_WITH_INTERNET), Mockito.eq(TimeUnit.SECONDS));
    }

    @Test
    public void testNextSchedulingWhenNoInternet() {
        Mockito.verify(mockScheduledExecutorService, Mockito.times(1))
               .execute(Mockito.any(Runnable.class));

        stubScheduledExecutorService.executeNext();

        Mockito.verify(mockScheduledExecutorService, Mockito.times(2))
               .schedule(Mockito.any(Runnable.class), Mockito.eq(NetworkConnectionSurveillanceImpl.DELAY_TIME_NO_INTERNET), Mockito.eq(TimeUnit.SECONDS));
    }

    @Test
    public void testNextSchedulingWhenHasInternet() {
        Mockito.verify(mockScheduledExecutorService, Mockito.times(1))
               .execute(Mockito.any(Runnable.class));

        Mockito.when(mockConnectivityInformation.isConnectedToNetwork()).thenReturn(true);
        Mockito.when(mockInternetAddressResolver.canResolveAddress()).thenReturn(true);

        stubScheduledExecutorService.executeNext();

        Mockito.verify(mockScheduledExecutorService, Mockito.times(1))
               .schedule(Mockito.any(Runnable.class), Mockito.eq(NetworkConnectionSurveillanceImpl.DELAY_TIME_WITH_INTERNET), Mockito.eq(TimeUnit.SECONDS));
    }

    @Test
    public void testForcedCheck() {
        Mockito.when(mockConnectivityInformation.isConnectedToNetwork()).thenReturn(true);
        Mockito.when(mockInternetAddressResolver.canResolveAddress()).thenReturn(true);

        networkConnectionSurveillance.forceCheck();

        Mockito.verify(mockScheduledExecutorService, Mockito.times(2))
               .execute(Mockito.any(Runnable.class));

        Mockito.verify(mockScheduledExecutorService, Mockito.times(1))
               .schedule(Mockito.any(Runnable.class), Mockito.eq(NetworkConnectionSurveillanceImpl.DELAY_TIME_WITH_INTERNET), Mockito.eq(TimeUnit.SECONDS));
    }
}
