package android.pixelface.timer;

public interface ITimer {
	boolean isRunning();

	void pause();

	/**
	 * Stops the timer. If the timer is started after a call to start, it has
	 * effectively been reset.
	 */
	void stop();

	/**
	 * Starts this timer.
	 */
	void start();

	/**
	 * Returns true if the interval this timer is defined over has elapsed.
	 * 
	 * @param time
	 *            time that's passed since this timer's last update
	 * @return whether this timer's interval has elapsed
	 */
	boolean updateTimer(long time);
}