package eu.fiveminutes.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;

public interface ResourceUtils {
    String STRING_RESOURCE = "string";
    String XML_RESOURCE = "xml";
    String DRAWABLE_RESOURCE = "drawable";
    String COLOR_RESOURCE = "color";
    String RAW_RESOURCE = "raw";

    String loadStringResourceByName(String resourceName);

    int getStringResourceIdByName(String resourceName);

    int getIdByName(String resourceName);

    Drawable loadDrawableResourceByName(String resourceName);

    Bitmap loadBitmapResourceByName(String resourceName);

    int loadColorResourceByName(String resourceName);

    String readStringFromRawResourceFile(int rawFileId);

    String getUriStringForResourceName(String resourceType, String resourceName);

    String getUriStringForResource(String resourceType, int resource);

    Uri getUriForResource(String resourceType, String resourceName);

    Uri getUriForResource(String resourceType, int resourceName);

    String loadRawResource(int resourceId);

    boolean isInTabletMode();

    boolean isLandscapeInTablet();
}
