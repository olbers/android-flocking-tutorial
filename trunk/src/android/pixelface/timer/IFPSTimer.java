package android.pixelface.timer;

public interface IFPSTimer {
	int getCurrentFPS();

	int getRequestedFPS();

	boolean isRunning();

	void setFPS(int fps);

	long sleep();

	void startTimer();

	void stopTimer();

	long getElapsedTime();
}
