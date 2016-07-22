package eu.fiveminutes.util;

import android.content.res.Resources;
import android.graphics.Point;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

public final class DisplayUtilsImpl implements DisplayUtils {

    private final Resources resources;
    private final WindowManager windowManager;

    public DisplayUtilsImpl(Resources resources, WindowManager windowManager) {
        this.resources = resources;
        this.windowManager = windowManager;
    }

    @Override
    public float getDpToPx(final int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
    }

    @Override
    public float getPxToDp(final int px) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, resources.getDisplayMetrics());
    }

    /**
     * This method returns screen height of a device
     *
     * @return screen height
     */
    @Override
    public int getScreenHeight() {
        final Display display = windowManager.getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);

        return size.y;
    }

    @Override
    public int getScreenWidth() {
        final Display display = windowManager.getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);

        return size.x;
    }

    @Override
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId);
        }

        return result;
    }

}
