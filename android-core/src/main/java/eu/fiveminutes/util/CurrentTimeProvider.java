package eu.fiveminutes.util;

public interface CurrentTimeProvider {

    long getCurrentTimeMillis();

    long getUnixTimestamp();
}
