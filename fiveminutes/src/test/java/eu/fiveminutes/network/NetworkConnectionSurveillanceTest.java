package eu.fiveminutes.network;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public final class NetworkConnectionSurveillanceTest {

    private final DummyScheduledExecutorService scheduledExecutorService = new DummyScheduledExecutorService();

    private ConnectivityInformation connectivityInformation;
    private InternetAddressResolver addressResolver;
    private NetworkConnectionSurveillance networkConnectionSurveillance;

    private NetworkConnectionSurveillance.Observer observer = Mockito.mock(NetworkConnectionSurveillance.Observer.class);

    @Before
    public void setUp() throws Exception {
        connectivityInformation = Mockito.mock(ConnectivityInformation.class);
        addressResolver = Mockito.mock(InternetAddressResolver.class);
        networkConnectionSurveillance = new NetworkConnectionSurveillanceImpl(connectivityInformation, addressResolver, scheduledExecutorService);
    }

    @Test
    public void testNoInternetConnectionIfNotConnectedToNetwork() {
        Mockito.when(connectivityInformation.isConnectedToNetwork()).thenReturn(false);
        Mockito.when(addressResolver.canResolveAddress()).thenReturn(false);
        scheduledExecutorService.executeNext();

        Assert.assertFalse(networkConnectionSurveillance.hasInternetConnection());
    }

    @Test
    public void testNoInternetConnectionIfConnectedToNetworkButNoInternet() {
        Mockito.when(connectivityInformation.isConnectedToNetwork()).thenReturn(true);
        Mockito.when(addressResolver.canResolveAddress()).thenReturn(false);
        scheduledExecutorService.executeNext();

        Assert.assertFalse(networkConnectionSurveillance.hasInternetConnection());
    }

    @Test
    public void testNoInternetConnectionIfConnectedToNetworkAndHasInternet() {
        Mockito.when(connectivityInformation.isConnectedToNetwork()).thenReturn(true);
        Mockito.when(addressResolver.canResolveAddress()).thenReturn(true);
        scheduledExecutorService.executeNext();

        Assert.assertTrue(networkConnectionSurveillance.hasInternetConnection());
    }

    @Test
    public void testObserverNotifiedWhenSubscribedNoInternet() {
        Mockito.when(connectivityInformation.isConnectedToNetwork()).thenReturn(true);
        Mockito.when(addressResolver.canResolveAddress()).thenReturn(false);

        scheduledExecutorService.executeNext();
        networkConnectionSurveillance.registerObserver(observer);

        Mockito.verify(observer).connectivityChange(false);
    }

    @Test
    public void testObserverNotifiedWhenSubscribedWithInternet() {
        Mockito.when(connectivityInformation.isConnectedToNetwork()).thenReturn(true);
        Mockito.when(addressResolver.canResolveAddress()).thenReturn(true);

        scheduledExecutorService.executeNext();
        networkConnectionSurveillance.registerObserver(observer);

        Mockito.verify(observer).connectivityChange(true);
    }

    @Test
    public void testObserverNotNotifiedWhenNoConnectionChange() {
        Mockito.when(connectivityInformation.isConnectedToNetwork()).thenReturn(true);
        Mockito.when(addressResolver.canResolveAddress()).thenReturn(true);

        scheduledExecutorService.executeNext();
        networkConnectionSurveillance.registerObserver(observer);

        scheduledExecutorService.executeNext();
        scheduledExecutorService.executeNext();
        scheduledExecutorService.executeNext();

        Mockito.verify(observer, Mockito.times(1)).connectivityChange(true);
    }

    @Test
    public void testObserverNotNotifiedWhenConnectionChangeFromConnectedToNotConnected() {
        Mockito.when(connectivityInformation.isConnectedToNetwork()).thenReturn(true);
        Mockito.when(addressResolver.canResolveAddress()).thenReturn(true);

        scheduledExecutorService.executeNext();
        networkConnectionSurveillance.registerObserver(observer);

        Mockito.when(addressResolver.canResolveAddress()).thenReturn(false);
        scheduledExecutorService.executeNext();

        Mockito.verify(observer, Mockito.times(1)).connectivityChange(true);
        Mockito.verify(observer, Mockito.times(1)).connectivityChange(false);
    }

    @Test
    public void testObserverNotNotifiedWhenConnectionChangeFromNotConnectedToConnected() {
        Mockito.when(connectivityInformation.isConnectedToNetwork()).thenReturn(true);
        Mockito.when(addressResolver.canResolveAddress()).thenReturn(false);

        scheduledExecutorService.executeNext();
        networkConnectionSurveillance.registerObserver(observer);

        Mockito.when(addressResolver.canResolveAddress()).thenReturn(true);
        scheduledExecutorService.executeNext();

        Mockito.verify(observer, Mockito.times(1)).connectivityChange(true);
        Mockito.verify(observer, Mockito.times(1)).connectivityChange(false);
    }

    @Test
    public void testUnregisterObserver() {
        Mockito.when(connectivityInformation.isConnectedToNetwork()).thenReturn(true);
        Mockito.when(addressResolver.canResolveAddress()).thenReturn(true);

        scheduledExecutorService.executeNext();
        networkConnectionSurveillance.registerObserver(observer);
        networkConnectionSurveillance.unregisterObserver(observer);

        Mockito.when(addressResolver.canResolveAddress()).thenReturn(false);
        scheduledExecutorService.executeNext();

        Mockito.verify(observer, Mockito.times(1)).connectivityChange(true);
    }
}
