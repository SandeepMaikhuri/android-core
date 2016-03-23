package eu.fiveminutes;

import android.test.AndroidTestCase;

import eu.fiveminutes.util.StringUtils;
import eu.fiveminutes.util.StringUtilsImpl;

public class StringUtilsTest extends AndroidTestCase {

    public void test() {
        final StringUtils stringUtils = new StringUtilsImpl();

        assertTrue(stringUtils.isStringEmpty(""));
        assertTrue(stringUtils.isStringEmpty(null));
        assertTrue(!stringUtils.isStringEmpty("hello!"));
    }
}
