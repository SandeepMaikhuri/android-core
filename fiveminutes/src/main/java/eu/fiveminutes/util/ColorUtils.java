package eu.fiveminutes.util;

public interface ColorUtils {

    int rgbStringToColor(String colorString, int defaultColor);

    int hexStringToColor(String colorString, int defaultColor);

    String rgbToHexStringColor(String rgbString, int defaultColor);

    String toHexString(int intColor);

    int addTransparencyToColor(int color, int alpha);

    int addTransparencyToColor(int color, float alpha);

    int calculateComplementaryColor(int color);

    int calculateLighterColor(int color, int percentage);

    int calculateDarkerColor(int color, int percentage);

}
