package eu.fiveminutes.dagger;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

/**
 * Base fragment which performs injection using the activity-scoped object graph
 */
public abstract class DaggerFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Assume that it lives within a DaggerActivity host
        ((DaggerActivity) getActivity()).inject(this);
    }

    protected <T> T getView(int id) {
        return (T) getView().findViewById(id);
    }

    protected boolean isActivityLive() {
        Activity activity = getActivity();
        if (activity == null || !(activity instanceof DaggerActivity)) {
            return false;
        }

        DaggerActivity daggerActivity = (DaggerActivity) activity;
        return daggerActivity.isActivityLive();
    }
}
