package eu.fiveminutes.util;

public interface CryptoUtils {
    String hmacSha1(String data, String key);

    int getUnixTimestamp();

    String getUnixTimestampAsString();
}
