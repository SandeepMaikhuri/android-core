package eu.fiveminutes.dagger;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Window;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Here it provides the dependencies those have same lifetime of one activity in your app
 */
@Module(
//        complete = true,    // Here we enable object graph validation
//        library = true,
//        addsTo = ApplicationScopeModule.class, // Important for object graph validation at compile time
//        injects = {
//                DaggerActivity.class,
//        }
)
public class ActivityScopeModule {

    private final DaggerActivity activity;

    public ActivityScopeModule(DaggerActivity activity) {
        this.activity = activity;
    }

    /**
     * Allow the application context to be injected but require that it be annotated with
     * {@link eu.fiveminutes.dagger.ForActivity @Annotation} to explicitly differentiate it from an activity context.
     */
    @Provides
    @Singleton
    @ForActivity
    Context provideActivityContext() {
        return activity;
    }

    @Provides
    @Singleton
    Activity provideActivity() {
        return activity;
    }


    @Provides
    @Singleton
    android.app.FragmentManager provideFragmentManager() {
        return activity.getFragmentManager();
    }

    @Provides
    @Singleton
    LayoutInflater provideLayoutInflater(@ForActivity Context activityContext) {
        return (LayoutInflater) activityContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Provides
    @Singleton
    Window provideWindow() {
        return activity.getWindow();
    }
}
