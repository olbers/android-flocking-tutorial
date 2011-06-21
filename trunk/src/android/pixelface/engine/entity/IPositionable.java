package android.pixelface.engine.entity;

/**
 * Represents a point in space. Necessary since not all developers want to use
 * {@link java.awt.Point Point}. This gives more flexibility.
 * 
 * @author Will Morrison
 * 
 */
public interface IPositionable {
	float getX();

	float getY();

	void setX(float x);

	void setY(float y);
}