package eu.fiveminutes.demo.common.module;

import android.content.Context;

import dagger.Module;

@Module(
//        complete = false,
//        library = true,
//        addsTo = ApplicationScopeModule.class,
//        injects = {
//                DemoApplication.class
//        }
)
public class DemoApplicationScopeModule {

    public final Context applicationContext;

    public DemoApplicationScopeModule(Context context) {
        this.applicationContext = context;
    }
}
