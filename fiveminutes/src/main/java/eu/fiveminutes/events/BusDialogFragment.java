package eu.fiveminutes.events;

import javax.inject.Inject;

import eu.fiveminutes.dagger.DaggerDialogFragment;

public abstract class BusDialogFragment extends DaggerDialogFragment {

    @Inject
    EventBus eventBus;

    @Override
    public void onResume() {
        super.onResume();
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
        super.onPause();
    }
}
