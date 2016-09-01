package eu.fiveminutes.network;

import android.os.Handler;
import android.os.Looper;

public final class MainThreadExecutorImpl implements MainThreadExecutor {

    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void execute(final Runnable runnable) {
        handler.post(runnable);
    }
}
