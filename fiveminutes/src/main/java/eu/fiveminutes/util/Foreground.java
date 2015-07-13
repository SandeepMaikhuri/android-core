package eu.fiveminutes.util;

public interface Foreground {

    String getForegroundActivityName();

    public interface Listener {

        public void onBecameForeground();

        public void onBecameBackground();

    }

    boolean isForeground();

    boolean isBackground();

    void addListener(ForegroundImpl.Listener listener);

    void removeListener(ForegroundImpl.Listener listener);
}
