package eu.fiveminutes.dagger;

import android.content.Context;
import android.net.ConnectivityManager;
import android.view.WindowManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.fiveminutes.events.EventBus;
import eu.fiveminutes.events.EventBusImpl;

@Module(
        complete = false,
        library = true,
        injects = {
                DaggerApplication.class
        }
)
public class ApplicationScopeModule {

    /* package */ public static Context applicationContext = null;

    /**
     * Allow the application context to be injected but require that it be annotated with
     * {@link eu.fiveminutes.dagger.ForApplication @Annotation} to explicitly differentiate it from an activity context.
     */
    @Provides
    @Singleton
    @ForApplication
    Context provideApplicationContext() {
        return applicationContext;
    }

    @Provides
    @Singleton
    ConnectivityManager provideConnectivityManager() {
        return (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Provides
    @Singleton
    WindowManager provideWindowManager() {
        return (WindowManager) applicationContext.getSystemService(Context.WINDOW_SERVICE);
    }

    @Provides
    @Singleton
    EventBus provideEventBus() {
        return new EventBusImpl();
    }
}
