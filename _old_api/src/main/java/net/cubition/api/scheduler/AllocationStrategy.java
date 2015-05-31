package net.cubition.api.scheduler;

/**
 * An AllocationStrategy allocates and controls Threads within a Scheduler.
 */
public interface AllocationStrategy {
	/**
	 * Returns the maximum amount of threads that this AllocationStrategy allows
	 * to be created.
	 *
	 * @return A maximum amount of threads. This must not change between method
	 *         calls.
	 */
	public int getMaxThreads();

	/**
	 * Creates a new thread, controlling the specified Runnable.
	 *
	 * @param r
	 *            The runnable to wrap with a Thread
	 * @return A new Thread
	 */
	public Thread newThread(Runnable r);
}
