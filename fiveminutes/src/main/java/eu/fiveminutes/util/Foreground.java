package eu.fiveminutes.util;

public interface Foreground {

    Class<?> getForegroundActivityClass();

    interface Listener {

        void onBecameForeground();

        void onBecameBackground();

    }

    boolean isForeground();

    boolean isBackground();

    void addListener(ForegroundImpl.Listener listener);

    void removeListener(ForegroundImpl.Listener listener);
}
