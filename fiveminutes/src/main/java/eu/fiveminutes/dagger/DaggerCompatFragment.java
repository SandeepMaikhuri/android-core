package eu.fiveminutes.dagger;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public abstract class DaggerCompatFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Assume that it lives within a DaggerActivity host
        ((DaggerActionBarActivity) getActivity()).inject(this);
    }

    protected <T> T getView(int id) {
        return (T) getView().findViewById(id);
    }

    protected boolean isActivityLive() {
        Activity activity = getActivity();
        if (activity == null || !(activity instanceof DaggerActionBarActivity)) {
            return false;
        }

        DaggerActionBarActivity daggerActivity = (DaggerActionBarActivity) activity;
        return daggerActivity.isActivityLive();
    }
}
