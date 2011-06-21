package android.pixelface.engine.object.sprite.flocking;

import android.pixelface.engine.entity.GameEntityGroup;
import android.pixelface.engine.entity.IPositionable;
import android.pixelface.engine.entity.sprite.IPhysicsSprite;
import android.pixelface.engine.geom.Vector2f;

public class AvoidLocationFlockRule extends AbstractFlockingRule {
	private float weight;
	private IPositionable pos;
	private float avoidRadius;
	private float r2;
	private static final Vector2f vect = new Vector2f(0, 0);

	public AvoidLocationFlockRule(GameEntityGroup<IPhysicsSprite> group,
			IPositionable pos, float factor, float avoidRadius) {
		super(group, factor);
		this.pos = pos;
		this.weight = factor;
		this.avoidRadius = avoidRadius;
		this.r2 = avoidRadius * avoidRadius;
	}

	@Override
	public Vector2f getRule(IPhysicsSprite p) {
		float tempx = p.getX() - pos.getX();
		float tempy = p.getY() - pos.getY();
		if (tempx * tempx + tempy * tempy < r2) {
			vect.setX(p.getX() - pos.getX());
			vect.setY(p.getY() - pos.getY());
		} else {
			vect.setX(0);
			vect.setY(0);
		}
		return vect;
	}

	@Override
	public void update(long time) {

	}
}
