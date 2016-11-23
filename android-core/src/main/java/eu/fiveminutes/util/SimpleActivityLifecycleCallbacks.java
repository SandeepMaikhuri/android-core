package eu.fiveminutes.util;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public abstract class SimpleActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    /**
     * Invoked when activity in the application is created.
     * @param activity instance that got created.
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        // NO OP - Keeps child classes from needing to override method that they don't use.
    }

    /**
     * Invoked when activity in the application is started.
     * @param activity instance that got started.
     */
    @Override
    public void onActivityStarted(Activity activity) {
        // NO OP - Keeps child classes from needing to override method that they don't use.
    }

    /**
     * Invoked when activity in the application is resumed.
     * @param activity instance that got resumed.
     */
    @Override
    public void onActivityResumed(Activity activity) {
        // NO OP - Keeps child classes from needing to override method that they don't use.
    }

    /**
     * Invoked when activity in the application is paused.
     * @param activity instance that got paused.
     */
    @Override
    public void onActivityPaused(Activity activity) {
        // NO OP - Keeps child classes from needing to override method that they don't use.
    }

    /**
     * Invoked when activity in the application is stopped.
     * @param activity instance that got stopped.
     */
    @Override
    public void onActivityStopped(Activity activity) {
        // NO OP - Keeps child classes from needing to override method that they don't use.
    }

    /**
     * Invoked when activity in the application runs {@link android.app.Activity#onSaveInstanceState(Bundle)}.
     * @param activity instance that got invoked.
     */
    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        // NO OP - Keeps child classes from needing to override method that they don't use.
    }

    /**
     * Invoked when activity in the application is destroyed.
     * @param activity instance that got destroyed.
     */
    @Override
    public void onActivityDestroyed(Activity activity) {
        // NO OP - Keeps child classes from needing to override method that they don't use.
    }
}