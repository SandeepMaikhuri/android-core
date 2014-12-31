package eu.fiveminutes.dagger;

import android.app.IntentService;

import dagger.ObjectGraph;

public abstract class DaggerIntentService extends IntentService implements Injector {

    private ObjectGraph objectGraph;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DaggerIntentService(String name) {
        super(name);
    }

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
