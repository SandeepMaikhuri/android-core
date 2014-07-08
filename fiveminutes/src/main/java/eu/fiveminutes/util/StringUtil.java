package eu.fiveminutes.util;

public class StringUtil {

    public static boolean isStringEmpty(String string) {
        if (string == null) {
            return true;
        }

        if (string.equals("")) {
            return true;
        }

        return false;
    }
}
