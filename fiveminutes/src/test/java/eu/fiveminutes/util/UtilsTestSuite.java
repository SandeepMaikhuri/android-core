package eu.fiveminutes.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                CryptoUtilsTest.class,
                ListUtilsTest.class,
                MathUtilsTest.class,
                StringUtilsTest.class
        }
)
public final class UtilsTestSuite {}
