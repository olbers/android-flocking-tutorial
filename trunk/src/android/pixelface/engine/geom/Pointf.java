package android.pixelface.engine.geom;

import android.pixelface.engine.entity.IPositionable;

public class Pointf implements IPositionable {
	private float x;
	private float y;

	public Pointf() {
		this(0, 0);
	}

	public Pointf(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void setLocation(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void translate(float dx, float dy) {
		setLocation(x += dx, y += dy);
	}

	@Override
	public void setX(float x) {
		this.x = x;
	}

	@Override
	public void setY(float y) {
		this.y = y;
	}

	@Override
	public float getX() {
		return this.x;
	}

	@Override
	public float getY() {
		return this.y;
	}

	public String toString() {
		return "[" + x + ", " + y + "]";
	}
}
