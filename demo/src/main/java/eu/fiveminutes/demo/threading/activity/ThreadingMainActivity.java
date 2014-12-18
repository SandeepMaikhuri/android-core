package eu.fiveminutes.demo.threading.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import eu.fiveminutes.demo.threading.fragment.ThreadingMainFragment;

/**
 * Created by tomo on 12/18/14.
 */
public class ThreadingMainActivity extends ThreadingContainerActivity {

    public static Intent createIntent(Context context) {
        return new Intent(context, ThreadingMainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        attachFragment(ThreadingMainFragment.createInstance(),
                ThreadingMainFragment.TAG,
                savedInstanceState);
    }
}
