package eu.fiveminutes.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import org.apache.http.util.ByteArrayBuffer;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

import eu.fiveminutes.dagger.ForApplication;

public final class ResourceUtilsImpl implements ResourceUtils {

    private final Context applicationContext;

    @Inject
    public ResourceUtilsImpl(@ForApplication Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public String loadStringResourceByName(String resourceName) {
        int resourceId = 0;
        String packageName = applicationContext.getPackageName();
        resourceId = applicationContext.getResources().getIdentifier(resourceName, "string", packageName);
        if (resourceId != 0) {
            return applicationContext.getResources().getString(resourceId);
        }

        return "";
    }

    @Override
    public int getStringResourceIdByName(String resourceName) {
        int resourceId = 0;
        String packageName = applicationContext.getPackageName();
        resourceId = applicationContext.getResources().getIdentifier(resourceName, "string", packageName);
        return resourceId;
    }

    @Override
    public int getIdByName(String resourceName) {
        String packageName = applicationContext.getPackageName();
        return applicationContext.getResources().getIdentifier(resourceName, "id", packageName);
    }

    @Override
    public Drawable loadDrawableResourceByName(String resourceName) {
        int resourceId = 0;
        String packageName = applicationContext.getPackageName();
        resourceId = applicationContext.getResources().getIdentifier(resourceName, "drawable", packageName);
        if (resourceId != 0) {
            return applicationContext.getResources().getDrawable(resourceId);
        }

        return null;
    }

    @Override
    public Bitmap loadBitmapResourceByName(String resourceName) {
        int resourceId = 0;
        String packageName = applicationContext.getPackageName();
        resourceId = applicationContext.getResources().getIdentifier(resourceName, "drawable", packageName);
        if (resourceId != 0) {
            return BitmapFactory.decodeResource(applicationContext.getResources(), resourceId);
        }

        return null;
    }

    @Override
    public int loadColorResourceByName(String resourceName) {
        int resourceId = 0;
        String packageName = applicationContext.getPackageName();
        resourceId = applicationContext.getResources().getIdentifier(resourceName, "color", packageName);
        if (resourceId != 0) {
            return applicationContext.getResources().getColor(resourceId);
        }

        return 0;
    }

    @Override
    public String readStringFromRawResourceFile(int rawFileId) {
        if (rawFileId <= 0) {
            return "";
        }

        InputStream is = applicationContext.getResources().openRawResource(rawFileId);
        BufferedInputStream bis = new BufferedInputStream(is);

        ByteArrayBuffer baf = new ByteArrayBuffer(50);

        int current = 0;
        try {
            while ((current = bis.read()) != -1) {
                baf.append((byte) current);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

        byte[] data = baf.toByteArray();
        return new String(data);
    }

    @Override
    public String getUriStringForResourceName(String resourceType, String resourceName) {
        String packageName = applicationContext.getPackageName();
        return String.format("android.resource://%s/%s/%s",
                packageName, resourceType, resourceName);
    }

    @Override
    public String getUriStringForResource(String resourceType, int resource) {
        String packageName = applicationContext.getPackageName();
        return String.format("android.resource://%s/%s/%d",
                packageName, resourceType, resource);
    }

    @Override
    public Uri getUriForResource(String resourceType, String resourceName) {
        return Uri.parse(getUriStringForResourceName(resourceType, resourceName));
    }

    @Override
    public Uri getUriForResource(String resourceType, int resourceName) {
        return Uri.parse(getUriStringForResource(resourceType, resourceName));
    }

    @Override
    public String loadRawResource(int resourceId) {
        String result = null;
        try {
            InputStream is = applicationContext.getResources().openRawResource(resourceId);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            os.write(buffer);
            os.close();
            is.close();
            result = os.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result != null ? result : "";
    }

    @Override
    public boolean isInTabletMode() {
        return applicationContext.getResources().getBoolean(eu.fiveminutes.R.bool.isTablet);
    }

    @Override
    public boolean isLandscapeInTablet() {
        return applicationContext.getResources().getBoolean(eu.fiveminutes.R.bool.isLandscapeInTablet);
    }
}
