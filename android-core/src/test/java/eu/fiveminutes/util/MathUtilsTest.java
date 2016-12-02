package eu.fiveminutes.util;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public final class MathUtilsTest {

    private MathUtils mathUtils;

    @Before
    public void setUp() {
        mathUtils = new MathUtilsImpl();
    }

    @Test
    public void testValueTransforming() {
        final int newValue = (int)mathUtils.getTransformedValue(50, 0, 100, 400, 800);
        Assert.assertEquals(600, newValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPassedValueOutOfIntervalException() {
        mathUtils.getTransformedValue(1000, 10, 20, 40, 50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOldMinGreaterThanOldMaxException() {
        mathUtils.getTransformedValue(0, 100, 0, 40, 50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewMinGreaterThanNewMaxException() {
        mathUtils.getTransformedValue(0, 0, 100, 50, 40);
    }
}
