package eu.fiveminutes.demo.common.activity;

import eu.fiveminutes.demo.example.ExampleActivity;
import eu.fiveminutes.demo.fonts.CustomFontActivity;
import eu.fiveminutes.demo.threading.activity.ThreadingMainActivity;

public class Activities {

    static Class<?>[] classes = {
            ExampleActivity.class,
            CustomFontActivity.class,
            ThreadingMainActivity.class

            // Add your demo activity classes here, like this:
            //, MyDemoActivity.class
    };
}
