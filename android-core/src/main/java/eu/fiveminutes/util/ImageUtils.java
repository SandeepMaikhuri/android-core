package eu.fiveminutes.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.squareup.picasso.Transformation;

import java.io.File;

public interface ImageUtils {
    void storeImage(Bitmap image, File pictureFile);

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    int getScreenHeight();

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    int getScreenWidth();

    void blurAndLoadImage(Context applicationContext, String url, ImageView target, String fileName);

    String getImageName(int resId);

    Bitmap getBlurredImage(Bitmap orgBitmap, String nameToSave, int radius);

    Transformation blurTransformation(String fileName);

    Transformation roundedTransformation(String filename, float radius);

    Transformation circleTransformation(String filename);
}
