package eu.fiveminutes.events;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.squareup.otto.Bus;

import javax.inject.Inject;

public final class EventBusImpl implements EventBus {

    private static final String TAG = EventBusImpl.class.getSimpleName();

    private final Bus bus;
    private final Handler uiHandler;

    @Inject
    public EventBusImpl() {
        bus = new Bus();
        uiHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(final Object event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            bus.post(event);
        } else {
            uiHandler.post(new Runnable() {
                @Override
                public void run() {
                    bus.post(event);
                }
            });
        }
    }

    @Override
    public void register(Object observer) {
        try {
            bus.register(observer);
        } catch (IllegalArgumentException ex) {
            Log.e(TAG, ex.getMessage());
        }
    }

    @Override
    public void unregister(Object observer) {
        try {
            bus.unregister(observer);
        } catch (IllegalArgumentException ex) {
            Log.e(TAG, ex.getMessage());
        }
    }
}
