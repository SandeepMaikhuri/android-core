package eu.fiveminutes.demo.threading.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import javax.inject.Inject;

import eu.fiveminutes.demo.R;
import eu.fiveminutes.demo.common.activity.DemoTopActivity;

public abstract class ThreadingContainerActivity extends DemoTopActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threading_container);
    }

    @Inject
    FragmentManager fragmentManager;

    protected Fragment attachFragment(Fragment fragment, String tag, Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.main_content_container, fragment, tag)
                    .commit();
        } else {
            fragment = fragmentManager.findFragmentByTag(tag);
        }

        return fragment;
    }
}
