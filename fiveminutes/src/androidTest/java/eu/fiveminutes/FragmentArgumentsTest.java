package eu.fiveminutes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.test.AndroidTestCase;

import eu.fiveminutes.ui.utils.FragmentArguments;

public class FragmentArgumentsTest extends AndroidTestCase {

  public void test(){
    Fragment fragment = new DummyFragment();
    FragmentArguments.putBoolean(fragment, "test", true);
    FragmentArguments.putLong(fragment, "long", 235);
    FragmentArguments.putInt(fragment, "int", 999);
    FragmentArguments.putString(fragment, "string", "pero");

    Bundle arguments = fragment.getArguments();
    assertEquals(arguments.getBoolean("test"), true);
    assertEquals(arguments.getLong("long"), 235);
    assertEquals(arguments.getInt("int"), 999);
    assertEquals(arguments.getString("string"), "pero");
  }


  public static class DummyFragment extends Fragment{

  }
}
