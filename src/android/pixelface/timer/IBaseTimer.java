package android.pixelface.timer;
public interface IBaseTimer {
	/**
	 * Used to determine if this timer is available for the current OS and
	 * architecture.
	 * 
	 * @return true if the timer loaded successfully and is available, false
	 *         otherwise
	 */
	public boolean available();

	/**
	 * Returns the number of ticks per second this timer generates.
	 * 
	 * @return expected ticks per second
	 */
	public long getResolution();

	/**
	 * Returns the current number of clock ticks this timer has generated. It is
	 * expected that once the timer is loaded, the ticks cannot be reset.
	 */
	public long getClockTicks();
}
