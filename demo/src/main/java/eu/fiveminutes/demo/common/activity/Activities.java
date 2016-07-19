package eu.fiveminutes.demo.common.activity;

import eu.fiveminutes.demo.example.BlurExampleActivity;
import eu.fiveminutes.demo.example.DisplayUtilsExampleActivity;
import eu.fiveminutes.demo.example.ExampleActivity;
import eu.fiveminutes.demo.example.NetworkSurveillanceActivity;
import eu.fiveminutes.demo.fonts.CustomFontActivity;

public class Activities {

    static Class<?>[] CLASSES = {
            BlurExampleActivity.class,
            CustomFontActivity.class,
            DisplayUtilsExampleActivity.class,
            ExampleActivity.class,
            NetworkSurveillanceActivity.class

            // Add your demo activity classes here, like this:
            //, MyDemoActivity.class
    };
}
