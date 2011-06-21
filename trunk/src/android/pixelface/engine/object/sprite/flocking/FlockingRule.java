package android.pixelface.engine.object.sprite.flocking;

import android.pixelface.engine.entity.IUpdateable;
import android.pixelface.engine.entity.sprite.IPhysicsSprite;
import android.pixelface.engine.geom.Vector2f;

/**
 * Represents a rule for boid movement within a {@link Flock}. Works by
 * returning vectors to influence boid movement.
 * 
 * @author Will Morrison
 * 
 */
public interface FlockingRule extends IUpdateable {
	/**
	 * Returns amount of weight this rule is given
	 * 
	 * @return amount weight this rule is given
	 */
	public float getWeight();

	/**
	 * Return a vector pointing in the direction the specified sprite should
	 * move.
	 * 
	 * @param p
	 *            specified sprite
	 * @return vector to update sprite's position with
	 */
	public Vector2f getRule(IPhysicsSprite p);
}