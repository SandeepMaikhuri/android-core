package eu.fiveminutes.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                CryptoUtilsTest.class,
                CurrentTImeProviderTest.class,
                GeoLocationUtilsTest.class,
                ListUtilsTest.class,
                MathUtilsTest.class,
                StopWatchTest.class,
                StringUtilsTest.class
        }
)
public final class UtilsTestSuite {}
