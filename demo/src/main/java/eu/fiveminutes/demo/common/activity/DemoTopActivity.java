package eu.fiveminutes.demo.common.activity;

import java.util.ArrayList;
import java.util.List;

import eu.fiveminutes.dagger.DaggerActivity;
import eu.fiveminutes.demo.common.module.DemoActivityScopeModule;

/**
 * Created by tomo on 12/18/14.
 */
public abstract class DemoTopActivity extends DaggerActivity {

    @Override
    protected List<Object> getActivityModules() {
        return new ArrayList<Object>() {{
            add(new DemoActivityScopeModule());
        }};
    }
}
