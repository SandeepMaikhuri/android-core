package eu.fiveminutes.network;

public interface MainThreadExecutor {

    void execute(Runnable runnable);
}
