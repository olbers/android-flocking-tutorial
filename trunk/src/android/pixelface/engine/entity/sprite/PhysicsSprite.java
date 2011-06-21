package android.pixelface.engine.entity.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.pixelface.engine.geom.CollisionRectangle;
import android.pixelface.engine.geom.IShape;
import android.pixelface.engine.geom.Vector2f;

public class PhysicsSprite implements IPhysicsSprite {
	private IShape collisionShape;

	private Bitmap holder;

	float maxVelocity;
	private float width;
	private float height;
	private float x;
	private float y;
	private float oldx;
	private float oldy;
	private Vector2f velocity;
	private Vector2f acceleration;
	private float rotation;

	public PhysicsSprite(Bitmap img, float x, float y) {
		this(img, x, y, img.getWidth(), img.getHeight());
	}

	public PhysicsSprite(Bitmap holder, float x, float y, float width,
			float height) {
		this.rotation = 0;
		this.maxVelocity = 15;
		this.holder = holder;
		this.x = x;
		this.y = y;
		this.oldx = x;
		this.oldy = y;
		this.collisionShape = new CollisionRectangle(x, y, width, height);
		velocity = new Vector2f(0, 0);
		acceleration = new Vector2f(0, 0);
	}

	public void setMaximumVelocity(float maxVelocity) {
		this.maxVelocity = maxVelocity;
	}

	public void setAccelerationX(float x) {
		this.acceleration.setX(x);
	}

	public void setAccelerationY(float y) {
		this.acceleration.setY(y);
	}

	public float getAccelerationX() {
		return this.acceleration.getX();
	}

	public float getAccelerationY() {
		return this.acceleration.getY();
	}

	public void setAcceleration(float x, float y) {
		this.acceleration.setX(x);
		this.acceleration.setY(y);
	}

	public void setVelocity(float x, float y) {
		setVelocityX(x);
		setVelocityY(y);
		if (velocity.length() > maxVelocity) {
			velocity.normalize();
			velocity.multiplication(maxVelocity);
		}
	}

	public void setVelocityX(float x) {
		this.velocity.setX(x);
	}

	public void setVelocityY(float y) {
		this.velocity.setY(y);
	}

	public float getVelocityX() {
		return this.velocity.getX();
	}

	public float getVelocityY() {
		return this.velocity.getY();
	}

	@Override
	public float getX() {
		return this.x;
	}

	@Override
	public float getY() {
		return this.y;
	}

	@Override
	public void setX(float x) {
		this.x = x;
	}

	@Override
	public void setY(float y) {
		this.y = y;
	}

	public void setLocation(float x, float y) {
		setX(x);
		setY(y);
	}

	protected void updateMovement(long time) {
		// alter location by velocity
		// setVelocityX(velocity.getX() + acceleration.getX());
		// setVelocityY(velocity.getY() + acceleration.getY());
		float dx = velocity.getX() * time;
		float dy = velocity.getY() * time;
		collisionShape.setLocation(collisionShape.getX() + dx, collisionShape.getY() + dy);
		oldx = getX();
		oldy = getY();
		this.setLocation(getX() + dx, getY() + dy);
		// set rotation based on velocity
		rotation = (float) Math.toDegrees(Math.atan2(velocity.getY(), velocity
				.getX()));

		// this.holder.setFlipped(getVelocityX() < 0);
	}

	public Bitmap getImage() {
		return this.holder;
	}

	protected void drawSprite(Canvas renderer) {
		renderer.drawBitmap(holder, 0, 0, null);
	}

	@Override
	public void render(Canvas renderer) {
		int cx = (int) (getX() - getWidth() / 2);
		int cy = (int) (getY() - getHeight() / 2);
		renderer.translate(cx, cy);
		renderer.rotate(rotation);
		drawSprite(renderer);
		renderer.rotate(-rotation);
		renderer.translate(-cx, -cy);
	}

	@Override
	public void update(long time) {
		updateMovement(time);
	}

	@Override
	public float getWidth() {
		return this.width;
	}

	@Override
	public float getHeight() {
		return this.height;
	}

	@Override
	public void setWidth(float width) {
		this.width = width;
	}

	@Override
	public void setHeight(float height) {
		this.height = height;
	}

	@Override
	public float getOldX() {
		return this.oldx;
	}

	@Override
	public float getOldY() {
		return this.oldy;
	}

}
