package eu.fiveminutes.demo.common.module;


/**
 * Here it provides the dependencies those have same lifetime of one activity in your app
 */

import dagger.Module;

@Module(
//        complete = true,    // Here we enable object graph validation
//        library = true,
//        addsTo = ActivityScopeModule.class, // Important for object graph validation at compile time
//        injects = {
//        }
)
public class DemoActivityScopeModule {
}
