package eu.fiveminutes.network;

final class StubMainThreadExecutor implements MainThreadExecutor {

    @Override
    public void execute(final Runnable runnable) {
        runnable.run();
    }
}
