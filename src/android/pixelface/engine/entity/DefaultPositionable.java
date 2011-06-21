package android.pixelface.engine.entity;


public class DefaultPositionable implements IPositionable {
	private float x;
	private float y;

	public DefaultPositionable() {
		this(0, 0);
	}

	public DefaultPositionable(float x, float y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public void setX(float x) {
		this.x = x;
	}

	@Override
	public void setY(float y) {
		this.y = y;
	}
}