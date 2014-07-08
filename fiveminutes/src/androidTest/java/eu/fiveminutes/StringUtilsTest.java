package eu.fiveminutes;

import android.test.AndroidTestCase;
import eu.fiveminutes.util.StringUtils;

public class StringUtilsTest extends AndroidTestCase {
    public void test() {
        assertTrue(StringUtils.isStringEmpty(""));
        assertTrue(StringUtils.isStringEmpty(null));
        assertTrue(!StringUtils.isStringEmpty("hello!"));
    }
}
