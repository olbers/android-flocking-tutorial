package android.pixelface.engine.object.sprite.flocking;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.pixelface.engine.entity.GameEntityGroup;
import android.pixelface.engine.entity.sprite.IPhysicsSprite;
import android.pixelface.engine.geom.Vector2f;

public class Flock implements GameEntityGroup<IPhysicsSprite> {
	/**
	 * Flocking rules used to determine this groups behavior.
	 */
	private List<FlockingRule> flockingRules;
	/**
	 * Positions of boids.
	 */
	private List<IPhysicsSprite> points;

	public Flock() {
		this(10);
	}

	public Flock(int initialSize) {
		points = new ArrayList<IPhysicsSprite>();
		flockingRules = new ArrayList<FlockingRule>(initialSize);
	}

	public boolean add(IPhysicsSprite position) {
		return points.add(position);
	}

	public boolean addFlockingRule(FlockingRule flockingRule) {
		return this.flockingRules.add(flockingRule);
	}

	public boolean removeFlockingRule(FlockingRule flockingRule) {
		return this.flockingRules.remove(flockingRule);
	}

	public void update(long elapsedTime) {
		// allow flocking rules to update internal state
		updateFlockingRules(elapsedTime);

		IPhysicsSprite p;
		FlockingRule f;
		Vector2f finalVelocity = new Vector2f(0, 0);
		Vector2f tempV = null;
		// update all sprite's variables with flocking behavior
		for (int i = 0; i < points.size(); i++) {
			p = points.get(i);
			// apply rules to all sprite
			for (int j = 0; j < flockingRules.size(); j++) {
				f = flockingRules.get(j);
				tempV = f.getRule(p);
				// alter rule by its weight
				tempV.multiplication(f.getWeight());
				// add rule to final vector
				finalVelocity.addition(tempV);
			}
			// normalize vector
			finalVelocity.normalize();
			// p.setAcceleration(finalVelocity.getX(), finalVelocity.getY());
			p.setVelocity(p.getVelocityX() + finalVelocity.getX(), p
					.getVelocityY()
					+ finalVelocity.getY());
		}

		// let sprite's update
		for (int i = 0; i < points.size(); i++) {
			p = points.get(i);
			p.update(elapsedTime);
		}
	}

	protected void updateFlockingRules(long elapsedTime) {
		for (int i = 0; i < flockingRules.size(); i++) {
			flockingRules.get(i).update(elapsedTime);
		}
	}

	public void render(Canvas renderer) {
		for (int i = 0; i < points.size(); i++) {
			points.get(i).render(renderer);
		}
	}

	@Override
	public int size() {
		return this.points.size();
	}

	@Override
	public IPhysicsSprite get(int index) {
		return this.points.get(index);
	}

	@Override
	public boolean remove(IPhysicsSprite t) {
		return this.points.remove(t);
	}
}
