package android.pixelface.engine.geom;

import android.pixelface.engine.entity.IPositionableForm;

public interface IShape extends IPositionableForm {

	/**
	 * Returns whether this collision shape intersects with other collision
	 * shape area.
	 */
	public boolean intersects(IShape shape);

	public boolean contains(IShape shape);

	/**
	 * Moves this collision shape to specified location.
	 */
	public void setLocation(float x, float y);

	/**
	 * Moves this collision shape by specified delta.
	 */
	public void translate(float dx, float dy);

	/**
	 * Sets the boundary of this collision shape to specified boundary.
	 */
	public void setBounds(float x1, float y1, float w1, float h1);

	/**
	 * Sets the width of this {@link IShape} instance.
	 * 
	 * @param width
	 *            The width of this {@link IShape} instance.
	 */
	void setWidth(int width);

	/**
	 * Sets the height of this {@link IShape} instance.
	 * 
	 * @param height
	 *            The height of this {@link IShape} instance.
	 */
	void setHeight(int height);
}
