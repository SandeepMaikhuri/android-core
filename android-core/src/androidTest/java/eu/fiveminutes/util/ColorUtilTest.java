package eu.fiveminutes.util;

import android.graphics.Color;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public final class ColorUtilTest {

    private ColorUtils colorUtils;

    @Before
    public void setUp() {
        colorUtils = new ColorUtilsImpl();
    }

    @Test
    public void testRgbStringToColorParsing() {
        final String colorRgb = "rgb (255, 255, 255)";
        final int colorInt = colorUtils.rgbStringToColor(colorRgb, Color.BLACK);

        Assert.assertEquals(0xFFFFFFFF, colorInt);
    }

    @Test
    public void testRgbStringToColorParsingReturnDefaultColor() {
        final String colorRgb = "";
        final int colorInt = colorUtils.rgbStringToColor(colorRgb, Color.RED);

        Assert.assertEquals(Color.RED, colorInt);
    }

    @Test
    public void testHexStringToColorParsing() {
        final String colorHex = "#FAFAFA";
        final int colorInt = colorUtils.hexStringToColor(colorHex, Color.BLACK);

        Assert.assertEquals(0xFFFAFAFA, colorInt);
    }

    @Test
    public void testHexStringToColorParsingReturningDefault() {
        final String colorHex = "#JFKSFJAKF";
        final int colorInt = colorUtils.hexStringToColor(colorHex, Color.RED);

        Assert.assertEquals(Color.RED, colorInt);
    }

    @Test
    public void testRgbToHexStringParsing() {
        final String rgbString = "rgb (255, 255, 255)";
        final String hexString = colorUtils.rgbToHexStringColor(rgbString, Color.BLACK);

        Assert.assertEquals("#FFFFFFFF", hexString);
    }

    @Test
    public void testRgbToHexStringParsingFallbackToDefault() {
        final String rgbString = "";
        final String hexString = colorUtils.rgbToHexStringColor(rgbString, Color.BLACK);

        Assert.assertEquals("#FF000000", hexString);
    }

    @Test
    public void testToHexString() {
        final int colorInt = Color.BLACK;
        final String colorHex = colorUtils.toHexString(colorInt);

        Assert.assertEquals("#FF000000", colorHex);
    }

    @Test
    public void testAddTransparencyToColor() {
        final int colorInt = colorUtils.addTransparencyToColor(Color.BLACK, 0.5f);

        Assert.assertEquals(0x7F000000, colorInt);
    }

    @Test
    public void testAddTransparencyToColor2() {
        final int colorInt = colorUtils.addTransparencyToColor(Color.BLACK, 0);

        Assert.assertEquals(0x00000000, colorInt);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddTransparencyToColorExceptionForTooSmallValue() {
        colorUtils.addTransparencyToColor(Color.BLACK, -100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddTransparencyToColorExceptionForTooLargeValue() {
        colorUtils.addTransparencyToColor(Color.BLACK, 300);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddTransparencyToColorExceptionForTooSmallValue2() {
        colorUtils.addTransparencyToColor(Color.BLACK, -1f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddTransparencyToColorExceptionForTooLargeValue2() {
        colorUtils.addTransparencyToColor(Color.BLACK, 2f);
    }

    @Test
    public void testCalculateComplementaryColor() {
        final int complementBlack = colorUtils.calculateComplementaryColor(Color.BLACK);
        Assert.assertEquals(Color.WHITE, complementBlack);

        final int complementRed = colorUtils.calculateComplementaryColor(Color.RED);
        //Color.RED is actually 0xFFFF000000.
        Assert.assertEquals(0xFF00FFFF, complementRed);
    }

    @Test
    public void testCalculateLighterColor() {
        //TODO
    }

    @Test
    public void testCalculateDarkerColor() {
        //TODO
    }
}
