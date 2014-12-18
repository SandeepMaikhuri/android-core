package eu.fiveminutes.demo.threading.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import javax.inject.Inject;

import eu.fiveminutes.demo.R;
import eu.fiveminutes.demo.common.activity.DemoTopActivity;

/**
 * Created by tomo on 12/18/14.
 */
public abstract class ThreadingContainerActivity extends DemoTopActivity {

    @Inject
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threading_container);
    }

    protected Fragment attachFragment(Fragment fragment, String tag, Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .add(fragment, tag)
                    .commit();
        } else {
            fragment = fragmentManager.findFragmentByTag(tag);
        }

        return fragment;
    }
}
