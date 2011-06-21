package android.pixelface.timer;

public class BasicTimer implements ITimer {
	private boolean running;
	private long interval;
	private long elapsedTime;

	public BasicTimer(long interval) {
		this.interval = interval;
		this.elapsedTime = 0;
		this.running = true;
	}

	public boolean isRunning() {
		return running;
	}

	public void pause() {
		this.running = false;
	}

	public void stop() {
		this.running = false;
		this.elapsedTime = 0;
	}

	public void start() {
		this.running = true;
	}

	@Override
	public boolean updateTimer(long time) {
		if (isRunning()) {
			this.elapsedTime += time;
			if (elapsedTime >= interval) {
				elapsedTime -= interval;
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
