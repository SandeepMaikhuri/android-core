package eu.fiveminutes.util;

public final class StringUtilsImpl implements StringUtils {

    @Override
    public boolean isStringEmpty(String string) {
        return string == null || "".equals(string);
    }

    @Override
    public String arrayToCsvString(String[] values) {
        String csv = "";
        for (int i = 0; i < values.length; i++) {
            csv = csv.concat(values[i]);
            if (i + 1 < values.length) {
                csv = csv.concat(", ");
            }
        }

        return csv;
    }
}
