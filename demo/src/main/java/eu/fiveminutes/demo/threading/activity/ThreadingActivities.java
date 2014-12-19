package eu.fiveminutes.demo.threading.activity;

import java.util.ArrayList;
import java.util.List;

import eu.fiveminutes.demo.example.ExampleActivity;
import eu.fiveminutes.demo.fonts.CustomFontActivity;

public final class ThreadingActivities {

    static Class<?>[] classes = {
            //TODO - Insert real example activities later
            ExampleActivity.class,
            CustomFontActivity.class,
            ThreadingMainActivity.class

            // Add your demo activity classes here, like this:
            //, MyDemoActivity.class
    };

    public static List<String> toStringList() {
        List<String> items = new ArrayList<String>();
        for (Class<?> demo : classes) {
            items.add(demo.getSimpleName().replaceFirst("Activity", ""));
        }
        return items;
    }
}
