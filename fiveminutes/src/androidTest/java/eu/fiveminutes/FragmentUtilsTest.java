package eu.fiveminutes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.test.AndroidTestCase;

import eu.fiveminutes.ui.utils.FragmentUtils;

public class FragmentUtilsTest extends AndroidTestCase {

  public void test(){
    Fragment fragment = new DummyFragment();
    FragmentUtils.setBoolean(fragment, "test", true);
    FragmentUtils.setLong(fragment, "long", 235);
    FragmentUtils.setInt(fragment, "int", 999);
    FragmentUtils.setString(fragment, "string", "pero");

    Bundle arguments = fragment.getArguments();
    assertEquals(arguments.getBoolean("test"), true);
    assertEquals(arguments.getLong("long"), 235);
    assertEquals(arguments.getInt("int"), 999);
    assertEquals(arguments.getString("string"), "pero");
  }


  public static class DummyFragment extends Fragment{

  }
}
