package eu.fiveminutes.util;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;

public interface Blur {
    @SuppressLint("NewApi")
    Bitmap fastblur(Bitmap sentBitmap, int radius);
}
