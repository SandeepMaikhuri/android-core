package eu.fiveminutes.util;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.widget.ImageView;

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

    public static void loadFromUrlAndResize(Context context, String url, ImageView imageView,
                                   @DrawableRes int placeHolderRes, int width, int height) {
        if (!TextUtils.isEmpty(url)) {
            with(context).load(url).resize(width, height).centerCrop()
                    .placeholder(placeHolderRes).into(imageView);
        } else {
            with(context).load(placeHolderRes).resize(width, height).centerCrop().into(imageView);
        }
    }

    public static void loadFromUrl(Context context, String url, ImageView imageView,
                                   @DrawableRes int placeHolderRes) {
        if (!TextUtils.isEmpty(url)) {
            with(context).load(url).placeholder(placeHolderRes).into(imageView);
        } else {
            with(context).load(placeHolderRes).into(imageView);
        }
    }

    public static void loadFromUrl(Context context, String url, ImageView imageView) {
        if (!TextUtils.isEmpty(url)) {
            PicassoWrapper.with(context).load(url).into(imageView);
        }
    }

    public static void loadFromUrlAndResize(Context context, String url, ImageView imageView,
                                   int width, int height) {
        if (!TextUtils.isEmpty(url)) {
            PicassoWrapper.with(context).load(url).resize(width, height).centerCrop().into(imageView);
        }
    }
}

