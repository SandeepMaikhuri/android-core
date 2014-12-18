package eu.fiveminutes.demo.threading.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import eu.fiveminutes.dagger.DaggerFragment;
import eu.fiveminutes.demo.R;

/**
 * Created by tomo on 12/18/14.
 */
public class ThreadingMainFragment extends DaggerFragment {

    public static final String TAG = ThreadingMainFragment.class.getSimpleName();

    public static ThreadingMainFragment createInstance() {
        return new ThreadingMainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_threading_main, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        ButterKnife.reset(this);
        super.onDestroyView();
    }
}
