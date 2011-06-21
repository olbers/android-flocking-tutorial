package android.pixelface.engine.entity.sprite;

public interface IPhysicsSprite extends ISprite {
	public float getOldX();

	public float getOldY();

	public void setVelocity(float x, float y);

	public void setAcceleration(float x, float y);

	public void setAccelerationX(float x);

	public void setAccelerationY(float y);

	public float getAccelerationX();

	public float getAccelerationY();

	public void setVelocityX(float x);

	public void setVelocityY(float y);

	public float getVelocityY();

	public float getVelocityX();
}
