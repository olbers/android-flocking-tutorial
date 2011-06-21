package android.pixelface.timer;

public class DefaultFPSTimer implements IFPSTimer {
	private IBaseTimer timer;
	private int FPS;
	private int currentFPS;
	private long sleepTimeNanos;
	private long timeSlept;
	private long sleepDebt;
	private int updates = 0;

	private long lastUpdate = 0;
	private long minSleep;

	private boolean active;
	private long lastElapsedTimeMS;

	public DefaultFPSTimer(IBaseTimer timer, int FPS) {
		this.timer = timer;
		if (FPS < 1) {
			throw new IllegalArgumentException("FPS must be greater than 0.");
		}
		this.FPS = FPS;
		this.sleepTimeNanos = 1000000000L / FPS;
		this.currentFPS = 0;
		this.timeSlept = 0L;
		this.sleepDebt = 0L;
		this.minSleep = 10;
		this.active = true;
	}

	public long getMinimumSleepTime() {
		return this.minSleep;
	}

	public void setMinimumSleepTime(long minSleep) {
		this.minSleep = minSleep;
	}

	@Override
	public int getRequestedFPS() {
		return this.FPS;
	}

	@Override
	public long getElapsedTime() {
		return this.lastElapsedTimeMS / 1000000;
	}

	@Override
	public int getCurrentFPS() {
		return this.currentFPS;
	}

	@Override
	public void startTimer() {
		this.lastUpdate = this.timer.getClockTicks();
		this.active = true;
	}

	@Override
	public boolean isRunning() {
		return this.active;
	}

	@Override
	public void stopTimer() {
		this.active = false;
	}

	public long sleep() {
		if (!active) {
			throw new IllegalStateException(
					"Timer was not started. Start timer before calling sleep.");
		}
		// time elapsed since last sleep was called
		long timeSinceLastCall = this.timer.getClockTicks() - this.lastUpdate;
		// time we want to sleep.
		long targetSleepTime = Math.max(0, sleepTimeNanos + sleepDebt
				- timeSinceLastCall);

		long actualSleepTime = 0;
		if (targetSleepTime != 0) {
			long time = timer.getClockTicks();
			int millis = (int) (targetSleepTime / 1000000);
			int nanos = (int) (targetSleepTime % 1000000);
			// System.out.println("Sleep debt: " + sleepDebt / 1000000000L + " "
			// + sleepDebt + " slept(" + millis + ", " + nanos
			// + ") sleep millis = " + sleepTimeNanos);
			try {
				Thread.sleep(millis, nanos);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			actualSleepTime = timer.getClockTicks() - time;
		}

		// negative if over slept, positive if under slept
		this.sleepDebt = (targetSleepTime - actualSleepTime);
		this.timeSlept += (actualSleepTime + timeSinceLastCall);
		// System.out.println("Elapsed: " + timeSlept / 1000000000L +
		// ", target="
		// + targetSleepTime / 1000000000L + " timeSlept=" + timeSlept
		// / (double) 1000000000L);
		this.updates++;
		if (timeSlept >= timer.getResolution()) {
			this.timeSlept -= timer.getResolution();
			this.currentFPS = this.updates;
			this.updates = 0;
		}
		this.lastUpdate = this.timer.getClockTicks();
		return (this.lastElapsedTimeMS = (actualSleepTime + timeSinceLastCall));
	}

	public static void main(String[] args) {
		DefaultFPSTimer timer = new DefaultFPSTimer(new NanoTimer(), 100);
		timer.startTimer();
		double avgSleep = 0;
		int count = 0;
		int totalSleep = 0;
		while (true) {
			timer.sleep();
			avgSleep = totalSleep / (double) (count == 0 ? 1 : count);
			System.out.println("\n" + "FPS: " + timer.currentFPS + " "
					+ avgSleep);
			try {
				int sleep = 20;// (int) (Math.random() * 20 + 1);
				totalSleep += sleep;
				++count;
				// Thread.sleep(sleep);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void setFPS(int fps) {
		this.FPS = fps;
		this.currentFPS = 0;
		this.timeSlept = 0;
	}
}