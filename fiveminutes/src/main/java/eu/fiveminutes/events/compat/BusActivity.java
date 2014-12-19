package eu.fiveminutes.events.compat;

import javax.inject.Inject;

import eu.fiveminutes.dagger.compat.DaggerActivity;
import eu.fiveminutes.events.EventBus;

public abstract class BusActivity extends DaggerActivity {

    @Inject
    EventBus eventBus;

    @Override
    protected void onResume() {
        super.onResume();
        eventBus.register(this);
    }

    @Override
    protected void onPause() {
        eventBus.unregister(this);
        super.onPause();
    }
}
