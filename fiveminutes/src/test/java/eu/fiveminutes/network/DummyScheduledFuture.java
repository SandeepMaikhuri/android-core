package eu.fiveminutes.network;

import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class DummyScheduledFuture implements ScheduledFuture<Runnable> {

    private final Runnable runnable;

    public DummyScheduledFuture(final Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public long getDelay(final TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(final Delayed another) {
        return 0;
    }

    @Override
    public boolean cancel(final boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Runnable get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public Runnable get(final long timeout, final TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}
