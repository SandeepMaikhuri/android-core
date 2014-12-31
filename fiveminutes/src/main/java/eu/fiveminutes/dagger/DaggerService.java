package eu.fiveminutes.dagger;

import android.app.Service;

import dagger.ObjectGraph;

/**
 * Created by tomo on 12/19/14.
 */
public abstract class DaggerService extends Service implements Injector {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        // Create the activity graph by .plus-ing our modules onto the application graph.
        DaggerApplication application = (DaggerApplication) getApplication();
        objectGraph = application.getObjectGraph().plus();

        // Inject ourselves so subclasses will have dependencies fulfilled when this method returns.
        objectGraph.inject(this);
    }

    @Override
    public void onDestroy() {
        objectGraph = null;
        super.onDestroy();
    }

    @Override
    public void inject(Object object) {
        if (objectGraph != null) {
            objectGraph.inject(object);
        }
    }

    @Override
    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }
}
