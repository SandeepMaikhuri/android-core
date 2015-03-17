package eu.fiveminutes.util;

import android.graphics.Color;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ColorUtilsImpl implements ColorUtils {

    private static final int MAX_CHANNEL_VALUE = 255;

    private final Pattern rgbParserPattern;

    public ColorUtilsImpl() {
        this.rgbParserPattern = Pattern.compile("rgb *\\( *([0-9]+), *([0-9]+), *([0-9]+) *\\)");
    }

    @Override
    public int rgbStringToColor(String colorString, int defaultColor) {

        if (TextUtils.isEmpty(colorString)) {
            return defaultColor;
        }

        Matcher m = rgbParserPattern.matcher(colorString);
        if (m.matches()) {
            return Color.argb(255, //alpha
                    Integer.valueOf(m.group(1)),  // r
                    Integer.valueOf(m.group(2)),  // g
                    Integer.valueOf(m.group(3))); // b
        }

        return defaultColor;
    }

    @Override
    public int hexStringToColor(String colorString, int defaultColor) {
        return !TextUtils.isEmpty(colorString) ? Color.parseColor(colorString) : defaultColor;
    }

    @Override
    public String toHexString(int intColor) {
        return String.format("#%06X", 0xFFFFFF & intColor);
    }

    @Override
    public int addTransparencyToColor(int color, int alpha) {
        return Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
    }

    @Override
    public int addTransparencyToColor(int color, float alpha) {
        final int int_alpha = (int)(MAX_CHANNEL_VALUE * alpha);
        return addTransparencyToColor(color, int_alpha);
    }

    @Override
    public int calculateComplementaryColor(int color) {
        // get existing colors
        int alpha = Color.alpha(color);
        int red = Color.red(color);
        int blue = Color.blue(color);
        int green = Color.green(color);

        // find compliments
        red = (~red) & 0xff;
        blue = (~blue) & 0xff;
        green = (~green) & 0xff;

        return Color.argb(alpha, red, green, blue);
    }

    @Override
    public int calculateLighterColor(int color, int percentage) {

        float perc = percentage / 100.0f + 1;
        // get existing colors
        int alpha = Color.alpha(color);
        int red = Color.red(color);
        int blue = Color.blue(color);
        int green = Color.green(color);

        red = (int) (perc * red);
        blue = (int) (perc * blue);
        green = (int) (perc * green);

        red = red > 255 ? 255 : red;
        blue = blue > 255 ? 255 : blue;
        green = green > 255 ? 255 : green;

        return Color.argb(alpha, red, green, blue);
    }

    @Override
    public int calculateDarkerColor(int color, int percentage) {

        float perc = 1 - percentage / 100.0f;
        // get existing colors
        int alpha = Color.alpha(color);
        int red = Color.red(color);
        int blue = Color.blue(color);
        int green = Color.green(color);

        red = (int) (perc * red);
        blue = (int) (perc * blue);
        green = (int) (perc * green);

        return Color.argb(alpha, red, green, blue);
    }
}
