package net.cubition.api.scheduler;

import java.util.concurrent.*;

/**
 * The scheduler delegates threads to everyone who wants one, via a reasonably
 * fair system.
 */
public class Scheduler {
    private final ScheduledExecutorService service;
    private final AllocationStrategy strategy;

    /**
     * Creates a new active Scheduler with the attached AllocationStrategy.
     *
     * @param strategy The Strategy to negotiate thread management with.
     */
    public Scheduler(AllocationStrategy strategy) {
        this.strategy = strategy;

        service = Executors.newScheduledThreadPool(strategy.getMaxThreads(), strategy::newThread);
    }

    /**
     * Posts a runnable to occur ASAP.
     *
     * @param runnable The runnable to run.
     * @return A future to control the Runnable with
     */
    public Future<?> post(Runnable runnable) {
        return service.submit(runnable);
    }

    /**
     * Posts a runnable to occur at a specified time.
     *
     * @param runnable The runnable to run.
     * @param time The time, in milliseconds from now, to run the task.
     * @return A future to control the Runnable with
     */
    public ScheduledFuture<?> post(Runnable runnable, long time) {
        return service.schedule(runnable, time, TimeUnit.MILLISECONDS);
    }

    /**
     * Posts a runnable to occur at a specified time, and repeat itself
     *
     * @param runnable The runnable to run.
     * @param initialPeriod The time, in milliseconds from now, to run the task first.
     * @param interval The time, in milliseconds from the previous interval, to run the task after the initialPeriod.
     * @return A future to control the Runnable with
     */
    public ScheduledFuture<?> post(Runnable runnable, long initialPeriod, long interval) {
        return service.scheduleAtFixedRate(runnable, initialPeriod, interval, TimeUnit.MILLISECONDS);
    }

    /**
     * Shuts down this Scheduler, stopping any further Runnables from being posted.
     */
    public void shutdown() {
        service.shutdown();
    }

    /**
     * Returns the AllocationStrategy currently in use with this Scheduler.
     * @return The current AllocationStrategy
     */
    public AllocationStrategy getAllocationStrategy() {
        return strategy;
    }
}
