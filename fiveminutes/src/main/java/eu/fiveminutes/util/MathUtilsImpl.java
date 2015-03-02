package eu.fiveminutes.util;

public final class MathUtilsImpl implements MathUtils {

    /**
     * This method create linear transformation. Example: Scaling 255 -> 0 to
     * 0-100% scale
     *
     * @param value  to convert
     * @param oldMax old scale maximum
     * @param oldMin old scale minimum
     * @param newMax new scale maximum
     * @param newMin new scale minimum
     * @return number transformed to new scale
     */
    @Override
    public float getTransformedValue(int value, int oldMax, int oldMin,
                                     float newMax, float newMin) {

        int oldRange = (oldMax - oldMin);
        float newRange = (newMax - newMin);
        float newValue = (((value - oldMin) * newRange) / (float) oldRange)
                + newMin;

        return newValue;
    }
}
