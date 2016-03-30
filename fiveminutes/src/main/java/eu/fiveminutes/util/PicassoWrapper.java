package eu.fiveminutes.util;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Cache;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public final class PicassoWrapper {

    static Cache CACHE;

    static Picasso INSTANCE = null;

    public static Picasso with(final Context context) {
        if (INSTANCE == null) {
            synchronized (PicassoWrapper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Picasso.Builder(context)
//                            .executor(Executors.newSingleThreadExecutor())
                            .memoryCache(getCache(context))
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static Cache getCache(final Context context) {
        if (CACHE == null) {
            synchronized (PicassoWrapper.class) {
                if (CACHE == null) {
                    CACHE = new LruCache(context);
                }
            }
        }
        return CACHE;
    }

    public static void loadFromUrlAndResize(final Context context, final String url, final ImageView imageView,
                                            @DrawableRes final int placeHolderRes, final int width, final int height) {
        if (!TextUtils.isEmpty(url)) {
            with(context).load(url).resize(width, height).placeholder(placeHolderRes).centerCrop().into(imageView);
        } else {
            with(context).load(placeHolderRes).resize(width, height).centerCrop().into(imageView);
        }
    }

    public static void loadFromUrl(final Context context, final String url, final ImageView imageView,
                                   @DrawableRes final int placeHolderRes) {
        if (!TextUtils.isEmpty(url)) {
            with(context).load(url).placeholder(placeHolderRes).into(imageView);
        } else {
            with(context).load(placeHolderRes).into(imageView);
        }
    }

    public static void loadFromUrl(final Context context, final String url, final ImageView imageView) {
        if (!TextUtils.isEmpty(url)) {
            PicassoWrapper.with(context).load(url).into(imageView);
        }
    }

    public static void loadFromUrl(final Context context, final String url, final Target target) {
        if (!TextUtils.isEmpty(url)) {
            PicassoWrapper.with(context).load(url).into(target);
        }
    }

    public static void loadFromUrl(final Context context, final String url, final Target target, final int width, final int height) {
        if (!TextUtils.isEmpty(url)) {
            PicassoWrapper.with(context).load(url).resize(width, height).centerCrop().into(target);
        }
    }

    public static void loadFromUrl(final Context context, final String url, final Target target,
                                   @DrawableRes final int placeholder, final int width, final int height) {
        if (!TextUtils.isEmpty(url)) {
            PicassoWrapper.with(context).load(url).resize(width, height).placeholder(placeholder).centerCrop().into(target);
        }
    }

    public static void loadFromUrlAndResize(final Context context, final String url, final ImageView imageView,
                                            final int width, final int height) {
        if (!TextUtils.isEmpty(url)) {
            PicassoWrapper.with(context).load(url).resize(width, height).centerCrop().into(imageView);
        }
    }
}

