package eu.fiveminutes.util;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Handler;

import java.util.HashSet;
import java.util.Set;

/**
 * Usage:
 * <p>
 * 1. Get the ForegroundImpl Singleton, passing a Context or Application object unless you
 * are sure that the Singleton has definitely already been initialised elsewhere.
 * <p>
 * 2.a) Perform a direct, synchronous check: ForegroundImpl.isForeground() / .isBackground()
 * <p>
 * or
 * <p>
 * 2.b) Register to be notified (useful in Service or other non-UI components):
 * <p>
 * ForegroundImpl.Listener myListener = new ForegroundImpl.Listener(){
 * public void onBecameForeground(){
 * // ... whatever you want to do
 * }
 * public void onBecameBackground(){
 * // ... whatever you want to do
 * }
 * }
 * <p>
 * public void onCreate(){
 * super.onCreate();
 * ForegroundImpl.get(this).addListener(listener);
 * }
 * <p>
 * public void onDestroy(){
 * super.onCreate();
 * ForegroundImpl.get(this).removeListener(listener);
 * }
 */
public final class ForegroundImpl extends SimpleActivityLifecycleCallbacks implements Foreground {

    private static final int FOREGROUND_CHECK_DELAY_PERIOD = 1500;

    private final Handler handler;
    private final Set<Foreground.Listener> listeners = new HashSet<>();

    private Class<?> foregroundActivityClass;

    private Runnable check;

    private boolean foreground;
    private boolean paused = true;

    public ForegroundImpl(Application application, Handler handler) {
        this.handler = handler;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            application.registerActivityLifecycleCallbacks(this);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onActivityResumed(final Activity activity) {
        super.onActivityResumed(activity);

        final boolean wasBackground = !foreground;
        resetVariablesUponActivityResume(activity.getClass());
        removePendingDelayedForegroundChecks();

        if (wasBackground) {
            dispatchAppWentForeground();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onActivityPaused(final Activity activity) {
        super.onActivityPaused(activity);
        paused = true;
        foregroundActivityClass = null;
        removePendingDelayedForegroundChecks();
        check = new DelayedForegroundCheck();
        handler.postDelayed(check, FOREGROUND_CHECK_DELAY_PERIOD);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isForeground() {
        return foreground;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBackground() {
        return !isForeground();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addListener(final Listener listener) {
        listeners.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeListener(final Listener listener) {
        listeners.remove(listener);
    }

    /**
     * Method to retrieve {@link java.lang.Class} class for current foreground activity.
     *
     * @return {@link java.lang.Class} instance for current foreground activity.
     */
    public Class<?> getForegroundActivityClass() {
        return foregroundActivityClass;
    }

    /**
     * When app is resumed, there by in foreground, we want to pull information about current
     * foreground activity instance and set appropriate flags.
     *
     * @param foregroundActivityClass class file of activity that became foreground.
     */
    private void resetVariablesUponActivityResume(final Class<?> foregroundActivityClass) {
        paused = false;
        foreground = true;
        this.foregroundActivityClass = foregroundActivityClass;
    }

    /**
     * Informs all registered listeners that app went to foreground.
     */
    private void dispatchAppWentForeground() {
        for (Listener listener : listeners) {
            listener.onBecameForeground();
        }
    }

    /**
     * Informs all registered listeners that app went to background.
     */
    private void dispatchAppWentBackground() {
        for (Listener listener : listeners) {
            listener.onBecameBackground();
        }
    }

    /**
     * Removes scheduled delayed foreground check from handler queue. One of the reasons why we might do this is
     * because app went to foreground again before this check executed.
     */
    private void removePendingDelayedForegroundChecks() {
        if (check != null) {
            handler.removeCallbacks(check);
            check = null;
        }
    }

    /**
     * <p>
     * {@link java.lang.Runnable} implementation that checks whether app is currently in background.
     * </p>
     * <p>
     * We need this because of transition between activities. When one activity closes {@link android.app.Application.ActivityLifecycleCallbacks#onActivityPaused(Activity)}
     * will be invoked and therefore we might think we went to background. However, right after that
     * {@link android.app.Application.ActivityLifecycleCallbacks#onActivityResumed(Activity)} from next activity will be invoked.
     * </p>
     * <p>
     * To tackle this, we don't invoke {@link android.app.Application.ActivityLifecycleCallbacks#onActivityPaused(Activity)} when it happens,
     * but from this delayed check runnable when we are sure it happened.
     * </p>
     */
    private final class DelayedForegroundCheck implements Runnable {
        @Override
        public void run() {
            //After delayed run, we check whether app is still in background. If so, dispatch event to listeners.
            if (isAppStillInBackground()) {
                foreground = false;
                dispatchAppWentBackground();
            }
        }

        private boolean isAppStillInBackground() {
            return foreground && paused;
        }
    }
}

