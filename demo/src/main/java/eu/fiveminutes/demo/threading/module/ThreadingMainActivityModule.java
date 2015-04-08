package eu.fiveminutes.demo.threading.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.fiveminutes.dagger.ForApplication;
import eu.fiveminutes.demo.threading.activity.ThreadingActivities;
import eu.fiveminutes.demo.threading.adapter.ThreadingMainActivityAdapter;

@Module(
//        complete = true,    // Here we enable object graph validation
//        library = true,
//        addsTo = DemoActivityScopeModule.class, // Important for object graph validation at compile time
//        injects = {
//                ThreadingMainFragment.class,
//                ThreadingMainActivity.class
//        }
)
public class ThreadingMainActivityModule {

    @Provides
    @Singleton
    public ThreadingMainActivityAdapter provideThreadingMainActivityAdapter(
            @ForApplication Context context) {

        return new ThreadingMainActivityAdapter(
                context,
                android.R.layout.simple_list_item_1,
                ThreadingActivities.toStringList()
        );
    }
}
