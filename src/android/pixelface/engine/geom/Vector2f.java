package android.pixelface.engine.geom;

public class Vector2f implements Cloneable {
	private float x;
	private float y;

	public Vector2f() {
		this(0, 0);
	}

	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vector2f(Vector2f vector2) {
		this.x = vector2.x;
		this.y = vector2.y;
	}

	public float length() {
		return (float) Math.sqrt(x * x + y * y);
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public static Vector2f addition(Vector2f v1, Vector2f dest) {
		dest.x += v1.x;
		dest.y += v1.x;
		return dest;
	}

	public void addition(float x, float y) {
		this.x += x;
		this.y += y;
	}

	public void addition(Vector2f v) {
		this.addition(v.x, v.y);
	}
	
	public void normalize() {
		double mag1 = x * x + y * y;
		// check for rounding error
		if (Math.abs(mag1 - 1.0d) > 0.00001f) {
			double mag2 = Math.sqrt(mag1);
			if (mag2 != 0) {
				x /= mag2;
				y /= mag2;
			} else {
				x = 0;
				y = 0;
			}
		}
	}

	public static Vector2f subtract(Vector2f dest, Vector2f v1) {
		dest.x -= v1.x;
		dest.y -= v1.y;
		return dest;
	}

	public void subtraction(float x, float y) {
		this.x -= x;
		this.y -= y;
	}

	public void subtraction(Vector2f v) {
		this.subtraction(v.getX(), v.getY());
	}

	public static Vector2f multiply(Vector2f dest, float scalar) {
		dest.multiplication(scalar);
		return dest;
	}

	public void multiplication(float scalar) {
		this.x *= scalar;
		this.y *= scalar;
	}

	public void invert() {
		this.x = -this.x;
		this.y = -this.y;
	}

	public void division(float val) {
		if (val == 0) {
			throw new IllegalArgumentException("Cannot divide by 0.");
		}
		this.x /= val;
		this.y /= val;
	}

	public void interpolate(Vector2f v, float t) {
		this.x = v.x * (1F - t) + this.x * t;
		this.y = v.y * (1F - t) + this.y * t;
	}

	public static Vector2f interpolate(Vector2f a, Vector2f b, float t, Vector2f dst) {
		dst.x = a.x * (1.0f - t) + b.x * t;
		dst.y = a.y * (1.0f - t) + b.y * t;
		return dst;
	}

	public float dot(Vector2f v) {
		return this.x * v.x + this.y * v.y;
	}

	public float dot(float x, float y) {
		return this.x * x + this.y * y;
	}

	public Vector2f clone() {
		return new Vector2f(this.x, this.y);
	}

	public String toString() {
		return "[" + x + " " + y + "]";
	}

}
