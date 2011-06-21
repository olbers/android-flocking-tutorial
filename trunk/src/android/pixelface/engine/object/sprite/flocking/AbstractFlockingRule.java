package android.pixelface.engine.object.sprite.flocking;

import android.pixelface.engine.entity.GameEntityGroup;
import android.pixelface.engine.entity.sprite.IPhysicsSprite;

/**
 * Abstract class for implementation of flocking rules.
 * 
 * @author Will Morrison
 * 
 */
public abstract class AbstractFlockingRule implements FlockingRule {
	/**
	 * Group this flocking rule is influencing.
	 */
	private GameEntityGroup<IPhysicsSprite> group;
	/**
	 * Weight this flocking rule is given. If this flocking rules weight is
	 * relatively higher than other rules under consideration in a flock, it
	 * will have more influence on boid movement.
	 */
	private float weight;

	public AbstractFlockingRule(GameEntityGroup<IPhysicsSprite> group,
			float factor) {
		this.group = group;
		this.weight = factor;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	@Override
	public float getWeight() {
		return this.weight;
	}

	public GameEntityGroup<IPhysicsSprite> getGroup() {
		return this.group;
	}
}
