package eu.fiveminutes.network;

public final class StubMainThreadExecutor implements MainThreadExecutor {

    @Override
    public void execute(final Runnable runnable) {
        runnable.run();
    }
}
