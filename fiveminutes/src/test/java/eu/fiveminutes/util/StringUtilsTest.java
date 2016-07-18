package eu.fiveminutes.util;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public final class StringUtilsTest {

    private StringUtils stringUtils;

    @Before
    public void setUp() {
        stringUtils = new StringUtilsImpl();
    }

    @Test
    public void testIsStringEmptyOrNot() {
        Assert.assertTrue(stringUtils.isStringEmpty(""));
        Assert.assertTrue(stringUtils.isStringEmpty(null));
        Assert.assertTrue(!stringUtils.isStringEmpty("hello!"));
    }

    @Test
    public void testStringConcatenation() {
        final String[] values = {"1", "2", "3", "4", "5"};
        final String result = "1, 2, 3, 4, 5";

        Assert.assertEquals(result, stringUtils.arrayToCsvString(values));
    }

    @Test
    public void testStringConcatenationOfEmptyList() {
        Assert.assertEquals("", stringUtils.arrayToCsvString(new String[]{}));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStringConcatenationOfNull() {
        stringUtils.arrayToCsvString(null);
    }
}
