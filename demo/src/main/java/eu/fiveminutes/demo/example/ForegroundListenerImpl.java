package eu.fiveminutes.demo.example;

import android.util.Log;

import eu.fiveminutes.util.Foreground;

public final class ForegroundListenerImpl implements Foreground.Listener {

    private static final String TAG = ForegroundListenerImpl.class.getSimpleName();

    @Override
    public void onBecameForeground() {
        Log.d(TAG, "onBecameForeground");
    }

    @Override
    public void onBecameBackground() {
        Log.d(TAG, "onBecameBackground");
    }
}
