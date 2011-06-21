package android.pixelface.timer;
public class NanoTimer implements IBaseTimer {
	public NanoTimer() {

	}

	@Override
	public boolean available() {
		return true;
	}

	@Override
	public long getClockTicks() {
		return System.nanoTime();
	}

	@Override
	public long getResolution() {
		return 1000000000L;
	}
}
