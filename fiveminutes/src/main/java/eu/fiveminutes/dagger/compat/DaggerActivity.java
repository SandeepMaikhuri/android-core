package eu.fiveminutes.dagger.compat;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import eu.fiveminutes.dagger.DaggerApplication;
import eu.fiveminutes.dagger.Injector;

public abstract class DaggerActivity extends ActionBarActivity implements Injector {

    //private ObjectGraph activityGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Create the activity graph by .plus-ing our modules onto the application graph.
        DaggerApplication application = (DaggerApplication) getApplication();
        //activityGraph = application.getObjectGraph().plus(getActivityModules());

        // Inject ourselves so subclasses will have dependencies fulfilled when this method returns.
        //activityGraph.inject(this);

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        // Eagerly clear the reference to the activity graph to allow it to be garbage collected as
        // soon as possible.
        //activityGraph = null;

        super.onDestroy();
    }

    @Override
    public void inject(Object object) {
        /*if (activityGraph != null) {
            activityGraph.inject(object);
        }*/
    }

    /*@Override
    public ObjectGraph getObjectGraph() {
        return activityGraph;
    }*/

    protected abstract Object[] getActivityModules();

    public boolean isActivityLive() {
        return !isFinishing() && !isDestroyed();
    }
}
