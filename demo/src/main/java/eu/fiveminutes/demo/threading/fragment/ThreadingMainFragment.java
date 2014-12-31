package eu.fiveminutes.demo.threading.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import eu.fiveminutes.dagger.DaggerFragment;
import eu.fiveminutes.demo.R;
import eu.fiveminutes.demo.threading.adapter.ThreadingMainActivityAdapter;

public final class ThreadingMainFragment extends DaggerFragment {

    public static final String TAG = ThreadingMainFragment.class.getSimpleName();

    @Inject
    ThreadingMainActivityAdapter adapter;

    @InjectView(R.id.listview_examples)
    ListView listView;

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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        ButterKnife.reset(this);
        super.onDestroyView();
    }
}
