package eu.fiveminutes;

import android.os.Bundle;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import eu.fiveminutes.util.FragmentArguments;

@RunWith(AndroidJUnit4.class)
public final class FragmentArgumentsTest {

    @Test
    public void testAddingArgumentsToFragment() {
        final Fragment fragment = new DummyFragment();
        FragmentArguments.putBoolean(fragment, "test", true);
        FragmentArguments.putLong(fragment, "long", 235);
        FragmentArguments.putInt(fragment, "int", 999);
        FragmentArguments.putString(fragment, "string", "pero");

        final Bundle arguments = fragment.getArguments();
        Assert.assertEquals(arguments.getBoolean("test"), true);
        Assert.assertEquals(arguments.getLong("long"), 235);
        Assert.assertEquals(arguments.getInt("int"), 999);
        Assert.assertEquals(arguments.getString("string"), "pero");
    }

    public static class DummyFragment extends Fragment {

    }
}
