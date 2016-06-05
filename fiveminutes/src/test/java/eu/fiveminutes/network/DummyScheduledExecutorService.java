package eu.fiveminutes.network;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

final class DummyScheduledExecutorService implements ScheduledExecutorService {

    private final Queue<Runnable> runnableQueue = new ArrayBlockingQueue<>(100);

    public void executeNext() {
        runnableQueue.poll().run();
    }

    @NonNull
    @Override
    public ScheduledFuture<?> schedule(final Runnable command, final long delay, final TimeUnit unit) {
        runnableQueue.add(command);

        return new DummyScheduledFuture(command) {
            @Override
            public boolean cancel(final boolean mayInterruptIfRunning) {
                return runnableQueue.remove(command);
            }
        };
    }

    @NonNull
    @Override
    public <V> ScheduledFuture<V> schedule(final Callable<V> callable, final long delay, final TimeUnit unit) {
        throw new UnsupportedOperationException();
    }

    @NonNull
    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(final Runnable command, final long initialDelay, final long period, final TimeUnit unit) {
        throw new UnsupportedOperationException();
    }

    @NonNull
    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(final Runnable command, final long initialDelay, final long delay, final TimeUnit unit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void shutdown() {
        throw new UnsupportedOperationException();
    }

    @NonNull
    @Override
    public List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isShutdown() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isTerminated() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean awaitTermination(final long timeout, final TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    @NonNull
    @Override
    public <T> Future<T> submit(final Callable<T> task) {
        throw new UnsupportedOperationException();
    }

    @NonNull
    @Override
    public <T> Future<T> submit(final Runnable task, final T result) {
        throw new UnsupportedOperationException();
    }

    @NonNull
    @Override
    public Future<?> submit(final Runnable task) {
        task.run();

        return new DummyScheduledFuture(task);
    }

    @NonNull
    @Override
    public <T> List<Future<T>> invokeAll(final Collection<? extends Callable<T>> tasks) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    @NonNull
    @Override
    public <T> List<Future<T>> invokeAll(final Collection<? extends Callable<T>> tasks, final long timeout, final TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    @NonNull
    @Override
    public <T> T invokeAny(final Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T invokeAny(final Collection<? extends Callable<T>> tasks, final long timeout, final TimeUnit unit) throws InterruptedException,
                                                                                                                          ExecutionException,
                                                                                                                          TimeoutException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void execute(final Runnable command) {
        command.run();
    }
}
