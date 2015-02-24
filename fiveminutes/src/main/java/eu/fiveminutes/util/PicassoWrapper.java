package eu.fiveminutes.util;

import android.content.Context;

import com.squareup.picasso.Cache;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

public final class PicassoWrapper {

    static Cache cache;

    static Picasso singleton = null;

    public static Picasso with(Context context) {
        if (singleton == null) {
            synchronized (PicassoWrapper.class) {
                if (singleton == null) {
                    singleton = new Picasso.Builder(context)
                            .memoryCache(getCache(context))
                            .build();
                }
            }
        }
        return singleton;
    }

    public static Cache getCache(Context context) {
        if (cache == null) {
            synchronized (PicassoWrapper.class) {
                if (cache == null) {
                    cache = new LruCache(context);
                }
            }
        }
        return cache;
    }
}

