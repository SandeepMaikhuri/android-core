package eu.fiveminutes.dagger;

import android.app.Activity;
import android.os.Bundle;

import java.util.List;

import dagger.ObjectGraph;

public abstract class DaggerActivity extends Activity
        implements Injector {

    private ObjectGraph activityGraph;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Create the activity graph by .plus-ing our modules onto the application graph.
        DaggerApplication application = (DaggerApplication) getApplication();
        activityGraph = application.getObjectGraph().plus(getActivityModules());

        // Inject ourselves so subclasses will have dependencies fulfilled when this method returns.
        activityGraph.inject(this);

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        // Eagerly clear the reference to the activity graph to allow it to be garbage collected as
        // soon as possible.
        activityGraph = null;

        super.onDestroy();
    }

    protected <T> T getView(int id) {
        return (T) findViewById(id);
    }

    /**
     * Inject the supplied {@code object} using the activity-specific graph.
     */
    @Override
    public void inject(Object object) {
        if (activityGraph != null) {
            activityGraph.inject(object);
        }
    }

    @Override
    public ObjectGraph getObjectGraph() {
        return activityGraph;
    }

    protected abstract List<Object> getActivityModules();

    public boolean isActivityLive() {
        return !isFinishing() && !isDestroyed();
    }
}
