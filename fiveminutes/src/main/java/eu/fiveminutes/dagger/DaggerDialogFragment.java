package eu.fiveminutes.dagger;

import android.app.DialogFragment;
import android.os.Bundle;

public abstract class DaggerDialogFragment extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Assume that it lives within a DaggerActivity host
        ((DaggerActivity) getActivity()).inject(this);
    }
}
