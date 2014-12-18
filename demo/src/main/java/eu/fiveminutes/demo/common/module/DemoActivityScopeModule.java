package eu.fiveminutes.demo.common.module;

/**
 * Created by tomo on 12/18/14.
 */

/**
 * Here it provides the dependencies those have same lifetime of one activity in your app
 */

import dagger.Module;

@Module(
        complete = true,    // Here we enable object graph validation
        library = true,
        addsTo = DemoApplicationScopeModule.class, // Important for object graph validation at compile time
        injects = {
        }
)
public class DemoActivityScopeModule {
}
