package eu.fiveminutes.util;

import java.util.HashMap;
import java.util.Map;

public final class StopWatch {

    private static final String DEFAULT_TAG = "DEFAULT";
    private static Map<String, Interval> times = new HashMap<String, Interval>();

    //Starts measuring event for given tag
    public static void start(String tag) {
        times.put(tag, new Interval(System.currentTimeMillis(), System.currentTimeMillis()));
    }

    //Stops measuring event for given tag and returns elapsed time
    public static long stop(String tag) {
        if (times.containsKey(tag)) {
            Interval interval = times.get(tag);
            interval.running = false;
            times.get(tag).endTime = System.currentTimeMillis();
            return getElapsedTime(tag);
        }

        return -1L;
    }

    //Returns elapsed time for given tag and continues to measure
    public static long checkpoint(String tag) {
        if (times.containsKey(tag)) {
            return System.currentTimeMillis() - times.get(tag).startTime;
        }

        return -1L;
    }

    //Returns elapsed time for given tag
    public static long getElapsedTime(String tag) {
        if (times.containsKey(tag)) {
            Interval interval = times.get(tag);
            long endMoment = interval.running ? System.currentTimeMillis() : interval.endTime;
            return endMoment - interval.startTime;
        }

        return -1L;
    }

    //Resets stopwatch for given tag
    public static void reset(String tag) {
        times.remove(tag);
    }

    //Starts measuring event for default tag
    public static void start() {
        start(DEFAULT_TAG);
    }

    //Stops measuring event for default tag and returns elapsed time
    public static long stop() {
        return stop(DEFAULT_TAG);
    }

    //Returns elapsed time for default tag and continues to measure
    public static long checkpoint() {
        return checkpoint(DEFAULT_TAG);
    }

    //Returns elapsed time for default tag
    public static long getElapsedTime() {
        return getElapsedTime(DEFAULT_TAG);
    }

    //Resets stopwatch for default tag
    public static void reset() {
        reset(DEFAULT_TAG);
    }

    //Resets stopwatch for all tags
    public static void resetAll() {
        times = new HashMap<String, Interval>();
    }

    private static final class Interval {

        public long startTime = 0L;
        public long endTime = 0L;
        public boolean running = true;

        public Interval(long startTime, long endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public String toString() {
            return startTime + " - " + endTime;
        }
    }
}
