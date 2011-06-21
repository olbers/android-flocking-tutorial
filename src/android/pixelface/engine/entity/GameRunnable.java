package android.pixelface.engine.entity;

import android.content.Context;
import android.graphics.Canvas;
import android.pixelface.timer.DefaultFPSTimer;
import android.pixelface.timer.IFPSTimer;
import android.pixelface.timer.NanoTimer;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Handles updating and rendering the simulation's state.
 * 
 * @author Will Morrison
 * 
 */
public class GameRunnable extends SurfaceView implements Runnable {
	private IFPSTimer timer;
	private IUpdateable updateable;
	private IRenderable renderable;
	private boolean active;
	private SurfaceHolderCallback callback;
	private SurfaceHolder holder;

	public GameRunnable(Context context, IUpdateable updateable,
			IRenderable renderable) {
		super(context);
		this.updateable = updateable;
		this.renderable = renderable;
		this.callback = new SurfaceHolderCallback();
		this.active = false;
		this.holder = this.getHolder();
		holder.addCallback(this.callback);
		this.timer = new DefaultFPSTimer(new NanoTimer(), 100);
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return this.active;
	}

	public int getFPS() {
		return this.timer.getCurrentFPS();
	}

	@Override
	public void run() {
		this.active = true;
		this.timer.startTimer();
		while (active) {
			if (this.callback.isSurfaceValid()) {
				Canvas canvas = this.holder.lockCanvas();
				updateable.update(timer.getElapsedTime());
				this.renderable.render(canvas);
				this.holder.unlockCanvasAndPost(canvas);
				timer.sleep();
			}
		}
	}

	private class SurfaceHolderCallback implements SurfaceHolder.Callback {
		boolean valid;

		public boolean isSurfaceValid() {
			return valid;
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {

		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			this.valid = true;
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			this.valid = false;
		}

	}

}
