package eu.fiveminutes.util;

import android.graphics.Color;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ColorUtilsImpl implements ColorUtils {

    private static final int MAX_CHANNEL_VALUE = 255;

    private final Pattern rgbParserPattern;
    private final Pattern hexParserPattern;

    public ColorUtilsImpl() {
        this.rgbParserPattern = Pattern.compile("rgb *\\( *([0-9]+), *([0-9]+), *([0-9]+) *\\)");
        this.hexParserPattern = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
    }

    @Override
    public int rgbStringToColor(final String colorString, final int defaultColor) {

        if (TextUtils.isEmpty(colorString)) {
            return defaultColor;
        }

        final Matcher m = rgbParserPattern.matcher(colorString);
        if (m.matches()) {
            return Color.argb(255, //alpha
                              Integer.valueOf(m.group(1)),  // r
                              Integer.valueOf(m.group(2)),  // g
                              Integer.valueOf(m.group(3))); // b
        } else {
            return defaultColor;
        }
    }

    @Override
    public int hexStringToColor(final String colorString, final int defaultColor) {
        try {
            return !TextUtils.isEmpty(colorString) ? Color.parseColor(colorString) : defaultColor;
        } catch (final Exception e) {
            return defaultColor;
        }
    }

    @Override
    public String rgbToHexStringColor(final String rgbString, final int defaultColor) {

        if (rgbParserPattern.matcher(rgbString).matches()) {
            int color = rgbStringToColor(rgbString, defaultColor);
            return toHexString(color);
        }

        if (hexParserPattern.matcher(rgbString).matches()) {
            return rgbString;
        }

        return toHexString(defaultColor);
    }

    @Override
    public String toHexString(final int intColor) {
        //TODO I think this doesn't keep original alpha.
        return String.format("#FF%06X", 0xFFFFFF & intColor);
    }

    @Override
    public int addTransparencyToColor(final int color, final int alpha) {
        if (alpha < 0 || alpha > MAX_CHANNEL_VALUE) {
            throw new IllegalArgumentException("Alpha must be between 0 and 255.");
        }

        return Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
    }

    @Override
    public int addTransparencyToColor(final int color, final float alpha) {
        if (alpha < 0 || alpha > 1f) {
            throw new IllegalArgumentException("Alpha must be between 0 and 1.");
        }

        final int int_alpha = (int) (MAX_CHANNEL_VALUE * alpha);
        return addTransparencyToColor(color, int_alpha);
    }

    @Override
    public int calculateComplementaryColor(final int color) {

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
    public int calculateLighterColor(final int color, final int percentage) {

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
    public int calculateDarkerColor(final int color, final int percentage) {

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
