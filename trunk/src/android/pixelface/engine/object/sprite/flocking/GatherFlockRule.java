package android.pixelface.engine.object.sprite.flocking;

import java.util.Vector;

import android.pixelface.engine.entity.GameEntityGroup;
import android.pixelface.engine.entity.sprite.IPhysicsSprite;
import android.pixelface.engine.geom.Vector2f;


/**
 * A {@link FlockingRule} which calculates a {@link Vector vector} pointing
 * towards a {@link Flock flock's} average location. This average location
 * changes for each entity as an entity's position isn't considered in
 * determining its perception of the center of the flock.
 * 
 * @author Will Morrison
 * 
 */
public class GatherFlockRule extends AbstractFlockingRule {
	private Vector2f sumOfLocations;

	public GatherFlockRule(GameEntityGroup<IPhysicsSprite> group, float factor) {
		super(group, factor);
		sumOfLocations = new Vector2f(0, 0);
	}

	@Override
	public Vector2f getRule(IPhysicsSprite p) {
		// get average position excluding this position's values
		Vector2f v = new Vector2f(sumOfLocations.getX(), sumOfLocations.getY());
		v.subtraction(p.getX(), p.getY());
		v.division(this.getGroup().size() - 1);

		// calculate vector point toward group's average position
		v.subtraction(p.getX(), p.getY());
		return v;
	}

	/**
	 * Calculates the summation of all entities positions. This is used to
	 * determine each entity's perception of the flock's average location.
	 */
	@Override
	public void update(long time) {
		GameEntityGroup<IPhysicsSprite> group = this.getGroup();
		float x = 0, y = 0;
		for (int i = 0; i < group.size(); i++) {
			IPhysicsSprite e = group.get(i);
			x += e.getX();
			y += e.getY();
		}
		sumOfLocations.setX(x);
		sumOfLocations.setY(y);
	}
}
