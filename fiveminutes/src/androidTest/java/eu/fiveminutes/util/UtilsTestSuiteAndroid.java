package eu.fiveminutes.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                ColorUtilTest.class,
                ImageUtilsTest.class,
                IntentUtilsTest.class,
                ResourceUtilTest.class
        }
)
public final class UtilsTestSuiteAndroid { }
