package eu.fiveminutes.events;

import javax.inject.Inject;

import eu.fiveminutes.dagger.DaggerActivity;

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
