package eu.fiveminutes.ui.utils;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.io.Serializable;

public class FragmentUtils {
  private FragmentUtils() {
  }


  public static void setString(Fragment fragment, String key, String value) {
    Bundle args = fragment.getArguments() == null ? new Bundle() : fragment.getArguments();
    args.putString(key, value);
    fragment.setArguments(args);
  }

  public static void setBoolean(Fragment fragment, String key, boolean value) {
    Bundle args = fragment.getArguments() == null ? new Bundle() : fragment.getArguments();
    args.putBoolean(key, value);
    fragment.setArguments(args);
  }

  public static void setLong(Fragment fragment, String key, long value) {
    Bundle args = fragment.getArguments() == null ? new Bundle() : fragment.getArguments();
    args.putLong(key, value);
    fragment.setArguments(args);
  }


  public static void setInt(Fragment fragment, String key, int value) {
    Bundle args = fragment.getArguments() == null ? new Bundle() : fragment.getArguments();
    args.putInt(key, value);
    fragment.setArguments(args);
  }

  public static void setSerializable(Fragment fragment, String key, Serializable value) {
    Bundle args = fragment.getArguments() == null ? new Bundle() : fragment.getArguments();
    args.putSerializable(key, value);
    fragment.setArguments(args);
  }

  public static void setParcelable(Fragment fragment, String key, Parcelable value) {
    Bundle args = fragment.getArguments() == null ? new Bundle() : fragment.getArguments();
    args.putParcelable(key, value);
    fragment.setArguments(args);
  }


  public static void replace(FragmentManager manager, int id, Fragment fragment) {
    manager
        .beginTransaction()
        .replace(id, fragment)
        .commit();
  }
}

