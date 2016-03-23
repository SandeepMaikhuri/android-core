package eu.fiveminutes.util;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

import java.io.Serializable;

public final class FragmentArguments {

    private FragmentArguments() {

    }

    public static void putString(final Fragment fragment, final String key, final String value) {
        final Bundle args = getArgumentsOrDefaultBundle(fragment);
        args.putString(key, value);
        fragment.setArguments(args);
    }

    public static void putBoolean(final Fragment fragment, final String key, final boolean value) {
        final Bundle args = getArgumentsOrDefaultBundle(fragment);
        args.putBoolean(key, value);
        fragment.setArguments(args);
    }

    public static void putLong(final Fragment fragment, final String key, final long value) {
        final Bundle args = getArgumentsOrDefaultBundle(fragment);
        args.putLong(key, value);
        fragment.setArguments(args);
    }

    public static void putInt(final Fragment fragment, final String key, final int value) {
        final Bundle args = getArgumentsOrDefaultBundle(fragment);
        args.putInt(key, value);
        fragment.setArguments(args);
    }

    public static void putSerializable(final Fragment fragment, final String key, final Serializable value) {
        final Bundle args = getArgumentsOrDefaultBundle(fragment);
        args.putSerializable(key, value);
        fragment.setArguments(args);
    }

    public static void putParcelable(final Fragment fragment, final String key, final Parcelable value) {
        final Bundle args = getArgumentsOrDefaultBundle(fragment);
        args.putParcelable(key, value);
        fragment.setArguments(args);
    }

    private static Bundle getArgumentsOrDefaultBundle(final Fragment fragment) {
        final Bundle bundle = fragment.getArguments();

        return bundle != null ? bundle : new Bundle();
    }
}

