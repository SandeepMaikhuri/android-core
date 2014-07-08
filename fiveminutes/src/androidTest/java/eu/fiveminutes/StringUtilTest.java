package eu.fiveminutes;

import android.test.AndroidTestCase;
import eu.fiveminutes.util.StringUtil;

public class StringUtilTest extends AndroidTestCase {
    public void test() {
        assertTrue(StringUtil.isStringEmpty(""));
        assertTrue(StringUtil.isStringEmpty(null));
        assertTrue(!StringUtil.isStringEmpty("hello!"));
    }
}
