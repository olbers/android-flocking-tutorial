package android.pixelface.engine.geom;

import android.pixelface.engine.entity.IPositionableForm;

/**
 * Optimized <code>java.awt.Rectangle</code> with double precision. The default
 * sprite collision bounding box. <br />
 * <br />
 * This class was made final in 0.2.4, as it is not meant to be extended. GTGE
 * will consistently use the {@link CollisionShape} interface internally, so if
 * a new {@link CollisionShape} instance is required, it can be created without
 * subclassing the {@link CollisionRect} class.
 * 
 * @see CollisionShape
 */
public final class CollisionRectangle implements IShape, IPositionableForm {

	private float x;
	private float y;
	private float width;
	private float height;

	public CollisionRectangle(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * Creates new <code>CollisionRect</code>.
	 */
	public CollisionRectangle() {
		super();
	}

	/**
	 * Creates and returns a new {@link CollisionRect} instance with a copy of
	 * the position, height and width of the given non-null
	 * {@link CollisionShape} instance.
	 * 
	 * @param shape
	 *            The given non-null {@link CollisionShape} instance to copy.
	 * @throws IllegalArgumentException
	 *             Throws an {@link IllegalArgumentException} if the given
	 *             {@link CollisionShape} instance is null.
	 */
	public CollisionRectangle(IShape shape) {
		if (shape == null) {
			throw new IllegalArgumentException(
					"The CollisionShape instance may not be null.");
		}
		this.x = shape.getX();
		this.y = shape.getY();
		this.width = shape.getWidth();
		this.height = shape.getHeight();
	}

	@Override
	public boolean intersects(IShape shape) {
		return (this.x + this.width > shape.getX()
				&& this.x < shape.getX() + shape.getWidth()
				&& this.y + this.height > shape.getY() && this.y < shape.getY()
				+ shape.getHeight());
	}

	@Override
	public boolean contains(IShape shape) {
		return this.x <= shape.getX() && this.y <= shape.getY()
				&& this.width >= shape.getWidth()
				&& this.height >= shape.getHeight();
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

	@Override
	public void setLocation(float x, float y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void translate(float dx, float dy) {
		this.x += dx;
		this.y += dy;
	}

	@Override
	public void setBounds(float x1, float y1, float w1, float h1) {
		this.x = x1;
		this.y = y1;
		this.width = w1;
		this.height = h1;
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public void setWidth(float width) {
		this.width = width;
	}

	@Override
	public void setHeight(float height) {
		this.height = height;
	}

	public String toString() {
		return "CollisionRectangle: " + x + " " + y + " " + width + " "
				+ height;
	}
}