package eu.fiveminutes.dagger;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Base class of a dagger enabled application
 */
public abstract class DaggerApplication extends Application implements Injector {

    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        ApplicationScopeModule sharedAppModule = new ApplicationScopeModule();

        // bootstrap. So that it allows no-arg constructor in AndroidAppModule
        sharedAppModule.applicationContext = this.getApplicationContext();

        List<Object> modules = new ArrayList<Object>();
        modules.add(sharedAppModule);
        //modules.add(new UserAccountModule());
        //modules.add(new ThreadingModule());
        modules.addAll(getAppModules());

        objectGraph = ObjectGraph.create(modules.toArray());

        objectGraph.inject(this);
    }

    protected abstract List<Object> getAppModules();

    @Override
    public void inject(Object object) {
        objectGraph.inject(object);
    }

    @Override
    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }

    public static void inject(Context context, Object object) {
        Context applicationContext = context.getApplicationContext();
        if (applicationContext instanceof DaggerApplication) {
            ((DaggerApplication) applicationContext).inject(object);
        }
    }
}

