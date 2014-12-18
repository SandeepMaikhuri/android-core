package eu.fiveminutes.demo.common.application;

import java.util.ArrayList;
import java.util.List;

import eu.fiveminutes.dagger.DaggerApplication;
import eu.fiveminutes.demo.common.module.DemoApplicationScopeModule;

/**
 * Created by tomo on 12/18/14.
 */
public final class DemoApplication extends DaggerApplication {

    @Override
    protected List<Object> getAppModules() {
        return new ArrayList<Object>() {{
            add(new DemoApplicationScopeModule(DemoApplication.this));
        }};
    }
}
