package eu.fiveminutes.dagger.compat;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public abstract class DaggerDialogFragment extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Assume that it lives within a DaggerActivity host
        ((DaggerActivity) getActivity()).inject(this);
    }
}
