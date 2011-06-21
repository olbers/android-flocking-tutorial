package android.pixelface.engine.entity.sprite;

import android.graphics.Bitmap;

public class LimitTurnSprite extends PhysicsSprite {

	private float steeringMax;

	public LimitTurnSprite(Bitmap holder, float x, float y) {
		this(holder, x, y, holder.getWidth(), holder.getHeight(), 3f);
	}

	public LimitTurnSprite(Bitmap holder, float x, float y, float w, float h,
			float steeringMax) {
		super(holder, x, y, w, h);
		this.steeringMax = steeringMax;
	}

	public void setMaximumTurnAngle(float maxTurnAngle) {
		this.steeringMax = maxTurnAngle;
	}

	public float getMaximumTurnAngle() {
		return this.steeringMax;
	}

	@Override
	public void setVelocity(float x, float y) {
		float x1 = this.getVelocityX();
		float y1 = this.getVelocityY();
		// normalize sprite's velocity (no vector created each update)
		double mag1 = x1 * x1 + y1 * y1;
		// check for rounding error
		if (Math.abs(mag1 - 1.0d) > 0.00001f) {
			double mag2 = Math.sqrt(mag1);
			if (mag2 != 0) {
				x1 /= mag2;
				y1 /= mag2;
			} else {
				x1 = 0;
				y1 = 0;
			}
		}

		// save length of vector
		float length = (float) Math.sqrt(x * x + y * y);

		// normalize new velocity
		mag1 = x * x + y * y;
		// check for rounding error
		if (Math.abs(mag1 - 1.0d) > 0.00001f) {
			if (mag1 != 0) {
				double mag2 = Math.sqrt(mag1);
				x /= mag2;
				y /= mag2;
			} else {
				x = 0;
				y = 0;
			}
		}
		// convert to degrees to perform comparison
		double currentAngle = Math.toDegrees(Math.atan2(x1, y1)
				- Math.atan2(0, 1));
		double angle = Math.toDegrees(Math.atan2(y1, x1) - Math.atan2(y, x));
		// ensures sprite always turns optimally
		if (angle < 0 && angle < -180)
			angle += 360;
		else if (angle > 0 && angle > 180) {
			angle -= 360;
		}

		// System.out.printf(
		// "Light Sprite: current= %.3f, Max= %.3f, suggested= %.3f, ",
		// currentAngle, steeringMax, angle);
		if (angle < 0 && angle < -steeringMax) {
			angle = Math.toRadians(currentAngle - steeringMax);
			x = (float) Math.sin(angle);
			y = (float) Math.cos(angle);
		} else if (angle >= 0 && angle > steeringMax) {
			angle = Math.toRadians(currentAngle + steeringMax);
			x = (float) Math.sin(angle);
			y = (float) Math.cos(angle);
		}
		// System.out.printf(" Final= %.3f\n", Math.toDegrees(angle));
		super.setVelocity(x * length, y * length);
	}
}
