package eu.fiveminutes.util;

import android.test.AndroidTestCase;

public class StringUtilsTest extends AndroidTestCase {

    public void test() {
        final StringUtils stringUtils = new StringUtilsImpl();

        assertTrue(stringUtils.isStringEmpty(""));
        assertTrue(stringUtils.isStringEmpty(null));
        assertTrue(!stringUtils.isStringEmpty("hello!"));
    }
}
