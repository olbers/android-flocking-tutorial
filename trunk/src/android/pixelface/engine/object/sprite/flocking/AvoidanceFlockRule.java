package android.pixelface.engine.object.sprite.flocking;

import android.pixelface.engine.entity.GameEntityGroup;
import android.pixelface.engine.entity.sprite.IPhysicsSprite;
import android.pixelface.engine.geom.Vector2f;

/**
 * Keep boids a specified distance from one another from reducing that distance.
 * Basically boids are told to avoid one another.
 * 
 * @author Will Morrison
 */
public class AvoidanceFlockRule extends AbstractFlockingRule {
	private static final Vector2f vect = new Vector2f(0, 0);

	/**
	 * Minimum distance repel rule is activated during
	 */
	private float repelDistance;

	public AvoidanceFlockRule(GameEntityGroup<IPhysicsSprite> group, float factor,
			float repelDistance) {
		super(group, factor);
		this.repelDistance = repelDistance;
	}

	@Override
	public Vector2f getRule(IPhysicsSprite p) {
		int x = 0;
		int y = 0;
		float tempx, tempy;
		GameEntityGroup<IPhysicsSprite> group = this.getGroup();
		IPhysicsSprite sprite;
		float r2 = repelDistance * repelDistance;
		/**
		 * All sprites which are within a specified radius of one another are
		 * given a vector pointing away from each other.
		 */
		for (int i = 0; i < group.size(); i++) {
			sprite = group.get(i);
			if (sprite != p) {
				tempx = sprite.getX() - p.getX();
				tempy = sprite.getY() - p.getY();
				if (tempx * tempx + tempy * tempy < r2) {
					x -= (sprite.getX() - p.getX());
					y -= (sprite.getY() - p.getY());
				}
			}
		}
		vect.setX(x);
		vect.setY(y);
		return vect;
	}

	@Override
	public void update(long time) {
		// TODO Auto-generated method stub

	}

	public String toString() {
		return "AvoidRule: " + repelDistance;
	}
}