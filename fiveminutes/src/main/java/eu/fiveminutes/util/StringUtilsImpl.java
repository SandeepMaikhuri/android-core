package eu.fiveminutes.util;

public final class StringUtilsImpl implements StringUtils {

    private static final String EMPTY = "";

    @Override
    public boolean isStringEmpty(final String string) {
        return string == null || EMPTY.equals(string);
    }

    @Override
    public String arrayToCsvString(final String[] values) {
        if (values == null) {
            throw new IllegalArgumentException("values == null");
        }

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
