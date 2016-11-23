package eu.fiveminutes.util;

import android.annotation.SuppressLint;

public final class MathUtilsImpl implements MathUtils {

    /**
     * This method create linear transformation. Example: Scaling 255 -> 0 to
     * 0-100% scale
     *
     * @param value  to convert
     * @param oldMin old scale minimum
     * @param oldMax old scale maximum
     * @param newMin new scale minimum
     * @param newMax new scale maximum
     * @return number transformed to new scale
     */
    @SuppressLint("DefaultLocale")
    @Override
    public float getTransformedValue(final int value, final int oldMin, final int oldMax, final float newMin, final float newMax) {
        if (value < oldMin || value > oldMax) {
            throw new IllegalArgumentException(String.format("Value must be between %d and %d.", oldMin, oldMax));
        }

        if (oldMax <= oldMin) {
            throw new IllegalArgumentException("Old max must be greater than old min");
        }

        if (newMax <= newMin) {
            throw new IllegalArgumentException("New max must be greater than new min");
        }

        final int oldRange = (oldMax - oldMin);
        final float newRange = (newMax - newMin);

        return (((value - oldMin) * newRange) / (float) oldRange) + newMin;
    }
}
