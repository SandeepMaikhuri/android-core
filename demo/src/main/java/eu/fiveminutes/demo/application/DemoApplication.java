package eu.fiveminutes.demo.application;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import eu.fiveminutes.demo.example.ForegroundListenerImpl;
import eu.fiveminutes.util.Foreground;
import eu.fiveminutes.util.ForegroundImpl;

public final class DemoApplication extends Application {

    private Foreground foreground;

    private Foreground.Listener listener;

    @Override
    public void onCreate() {
        super.onCreate();
        foreground = new ForegroundImpl(this, new Handler(Looper.getMainLooper()));
        listener = new ForegroundListenerImpl();
        foreground.addListener(listener);

    }
}
