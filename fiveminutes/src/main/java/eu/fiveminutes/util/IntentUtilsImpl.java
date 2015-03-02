package eu.fiveminutes.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import eu.fiveminutes.dagger.ForApplication;

public final class IntentUtilsImpl implements IntentUtils {

    private final Context applicationContext;

    @Inject
    public IntentUtilsImpl(@ForApplication Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Intent getShareIntent(String subject, String text) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        sendIntent.setType("text/plain");
        return sendIntent;
    }

    @Override
    public Intent getShareImageIntent(Bitmap image, String imageName,
                                      String subject, String text) {

        FileOutputStream out = null;
        File file = new File(Environment.getExternalStorageDirectory() +
                File.separator + Environment.DIRECTORY_PICTURES + File.separator + imageName);
        try {
            out = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.JPEG, 100, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        sendIntent.setType("image/jpeg");
        return sendIntent;
    }

    @Override
    public Intent getStartActivityIntentByName(String activityName) {
        Intent intent = new Intent();
        String packageName = applicationContext.getPackageName();
        intent.setComponent(new ComponentName(packageName, activityName));
        return intent;
    }
}
