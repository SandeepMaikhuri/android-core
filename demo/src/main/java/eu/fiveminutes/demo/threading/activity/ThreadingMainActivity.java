package eu.fiveminutes.demo.threading.activity;

import android.os.Bundle;

import java.util.List;

import eu.fiveminutes.demo.threading.fragment.ThreadingMainFragment;
import eu.fiveminutes.demo.threading.module.ThreadingMainActivityModule;

public final class ThreadingMainActivity extends ThreadingContainerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        attachFragment(ThreadingMainFragment.createInstance(),
                ThreadingMainFragment.TAG,
                savedInstanceState);
    }

    @Override
    protected List<Object> getActivityModules() {
        List<Object> superModules = super.getActivityModules();
        superModules.add(new ThreadingMainActivityModule());
        return superModules;
    }
}
