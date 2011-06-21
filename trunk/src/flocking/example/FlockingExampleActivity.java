package flocking.example;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.pixelface.engine.entity.DefaultPositionable;
import android.pixelface.engine.entity.GameRunnable;
import android.pixelface.engine.entity.IUpdateable;
import android.pixelface.engine.entity.IPositionable;
import android.pixelface.engine.entity.IRenderable;
import android.pixelface.engine.entity.sprite.LimitTurnSprite;
import android.pixelface.engine.geom.Vector2f;
import android.pixelface.engine.object.sprite.flocking.AvoidanceFlockRule;
import android.pixelface.engine.object.sprite.flocking.Flock;
import android.pixelface.engine.object.sprite.flocking.GatherFlockRule;
import android.pixelface.engine.object.sprite.flocking.GatherLocationFlockRule;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;

/**
 * Entry point into the application.
 * 
 * @author Will Morrison
 * 
 */
public class FlockingExampleActivity extends Activity implements IUpdateable,
		IRenderable {
	String TAG = "MAIN";

	private Flock flock;
	private GatherLocationFlockRule gatherLocationRule;
	private AvoidanceFlockRule avoidRule;
	private float avoidWeight;
	private float avoidDistance;
	private float chaseWeight;
	private GameRunnable gameLoop;
	private Thread thread;
	private IPositionable target;
	private Paint textPaint;
	private int numSprites;
	
	boolean freeze = false;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e(TAG, "CREATING!");
		this.textPaint = new Paint();
		this.textPaint.setColor(Color.RED);
		this.setContentView(R.layout.main);
		this.flock = new Flock();

		Display display = this.getWindowManager().getDefaultDisplay();
		// calc center of screen
		float midx = display.getWidth() / 2;
		float midy = display.getHeight() / 2;
		Log.e(TAG, midx + " " + midy);
		this.chaseWeight = 1f;
		this.avoidWeight = 1f;
		this.avoidDistance = 20;
		this.numSprites = 100;
		int distance = Math.min(display.getWidth() - 200,
				display.getHeight() - 200);
		Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.wasp_png, null);

		// create all sprites in a circle
		for (int i = 0; i < numSprites; i++) {
			double rads = 2 * Math.PI * i / numSprites;
			float x = midx + (float) Math.cos(rads) * distance;
			float y = midy + (float) Math.sin(rads) * distance;
			// float x = (float) (midx + Math.random() * 50);
			// float y = (float) (midy + Math.random() * 50);
			int color = (int) (Math.random() * Integer.MAX_VALUE);
			color &= 0x5FFFFFFF;
			LimitTurnSprite sprite = new LimitTurnSprite(bitmap, x, y);
			Vector2f v = new Vector2f(x-midx, y-midy);
			v.normalize();
			sprite.setVelocity(v.getX(), v.getY());
			sprite.setMaximumTurnAngle(5);
			sprite.setMaximumVelocity(.2f);
			this.flock.add(sprite);
		}
		this.target = new DefaultPositionable(midx, midy);
		this.gatherLocationRule = new GatherLocationFlockRule(flock, target,
				chaseWeight);
		GatherFlockRule gatherRule = new GatherFlockRule(flock, 1);
		this.avoidRule = new AvoidanceFlockRule(flock, avoidWeight,
				avoidDistance);
		flock.addFlockingRule(gatherRule);
		flock.addFlockingRule(avoidRule);
		flock.addFlockingRule(this.gatherLocationRule);
		this.gameLoop = new GameRunnable(this, this, this);
		this.setContentView(this.gameLoop);
		this.thread = new Thread(this.gameLoop);
		this.thread.start();
	}

	@Override
	public boolean onTouchEvent(MotionEvent evt) {
		int action = evt.getAction();
		freeze = !freeze;
		if (action == MotionEvent.ACTION_DOWN
				|| action == MotionEvent.ACTION_MOVE) {
			this.target.setX((float) evt.getX());
			this.target.setY((float) evt.getY());
			this.gatherLocationRule.setWeight(this.chaseWeight * 400);
			this.avoidRule.setWeight(avoidWeight);
			LimitTurnSprite s = (LimitTurnSprite) flock.get(0);
			if (s.getMaximumTurnAngle() <= 5) {
				for (int i = 0; i < numSprites; i++) {
					LimitTurnSprite sprite = (LimitTurnSprite) flock.get(i);
					sprite.setMaximumTurnAngle(10);
				}
			}
			return true;
		} else {
			for (int i = 0; i < numSprites; i++) {
				LimitTurnSprite sprite = (LimitTurnSprite) flock.get(i);
				sprite.setMaximumTurnAngle(5);
			}
			this.gatherLocationRule.setWeight(this.chaseWeight);
			this.avoidRule.setWeight(avoidWeight * 40);
			return false;
		}
	}

	@Override
	public void update(long time) {
		
		if(freeze){
			time = 0;
		}
		flock.update(time);
	}

	@Override
	public void render(Canvas renderer) {
		renderer.drawColor(Color.BLACK);
		flock.render(renderer);
		String text = "FPS: " + this.gameLoop.getFPS();
		renderer.drawText(text, 10, 15, textPaint);
		renderer.drawText("Touch the screen, sprites will flock to your finger.", 100, 15, textPaint);
	}
}