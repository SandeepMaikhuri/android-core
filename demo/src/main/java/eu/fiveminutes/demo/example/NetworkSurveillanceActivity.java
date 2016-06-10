package eu.fiveminutes.demo.example;

import android.app.Activity;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import eu.fiveminutes.demo.R;
import eu.fiveminutes.network.ConnectivityInformation;
import eu.fiveminutes.network.ConnectivityInformationImpl;
import eu.fiveminutes.network.InternetAddressResolver;
import eu.fiveminutes.network.InternetAddressResolverImpl;
import eu.fiveminutes.network.MainThreadExecutorImpl;
import eu.fiveminutes.network.NetworkConnectionSurveillance;
import eu.fiveminutes.network.NetworkConnectionSurveillanceImpl;

public final class NetworkSurveillanceActivity extends Activity implements NetworkConnectionSurveillance.Observer{

    @InjectView(R.id.activity_network_surveillance_layout)
    protected LinearLayout rootLayout;

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    private NetworkConnectionSurveillance networkConnectionSurveillance;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_surveillance);
        injectViews();
        createObjects();
        networkConnectionSurveillance.registerObserver(this);
    }

    private void injectViews() {
        ButterKnife.inject(this);
    }

    private void createObjects() {
        final ConnectivityInformation connectivityInformation = new ConnectivityInformationImpl((ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE));
        final InternetAddressResolver internetAddressResolver = new InternetAddressResolverImpl("google.com");

        networkConnectionSurveillance = new NetworkConnectionSurveillanceImpl(connectivityInformation, internetAddressResolver,
                                                                              new MainThreadExecutorImpl(), scheduledExecutorService);
    }

    @Override
    public void connectivityChange(final boolean connected) {
        rootLayout.setBackgroundColor(connected ? Color.GREEN : Color.RED);
    }

    @OnClick(R.id.activity_network_surveillance_force_check)
    protected void onForceCheckClicked() {
        networkConnectionSurveillance.forceCheck();
    }
}
