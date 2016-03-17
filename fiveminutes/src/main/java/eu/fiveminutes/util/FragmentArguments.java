package eu.fiveminutes.util;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

import java.io.Serializable;

public final class FragmentArguments {

    private FragmentArguments() {

    }

    public static void putString(Fragment fragment, String key, String value) {
        Bundle args = getArgumentsOrDefaultBundle(fragment);
        args.putString(key, value);
        fragment.setArguments(args);
    }

    public static void putBoolean(Fragment fragment, String key, boolean value) {
        Bundle args = getArgumentsOrDefaultBundle(fragment);
        args.putBoolean(key, value);
        fragment.setArguments(args);
    }

    public static void putLong(Fragment fragment, String key, long value) {
        Bundle args = getArgumentsOrDefaultBundle(fragment);
        args.putLong(key, value);
        fragment.setArguments(args);
    }


    public static void putInt(Fragment fragment, String key, int value) {
        Bundle args = getArgumentsOrDefaultBundle(fragment);
        args.putInt(key, value);
        fragment.setArguments(args);
    }

    public static void putSerializable(Fragment fragment, String key, Serializable value) {
        Bundle args = getArgumentsOrDefaultBundle(fragment);
        args.putSerializable(key, value);
        fragment.setArguments(args);
    }

    public static void putParcelable(Fragment fragment, String key, Parcelable value) {
        Bundle args = getArgumentsOrDefaultBundle(fragment);
        args.putParcelable(key, value);
        fragment.setArguments(args);
    }

    private static Bundle getArgumentsOrDefaultBundle(Fragment fragment) {
        return fragment.getArguments() == null ? new Bundle() : fragment.getArguments();
    }
}

