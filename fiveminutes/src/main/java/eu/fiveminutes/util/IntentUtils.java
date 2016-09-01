package eu.fiveminutes.util;

import android.content.Intent;
import android.graphics.Bitmap;

public interface IntentUtils {

    Intent getShareIntent(String subject, String text);

    Intent getShareImageIntent(Bitmap image, String imageName,
                               String subject, String text);

    Intent getStartActivityIntentByName(String activityName);

}
