package android.pixelface.engine.object.sprite.flocking;

import android.pixelface.engine.entity.GameEntityGroup;
import android.pixelface.engine.entity.IPositionable;
import android.pixelface.engine.entity.sprite.IPhysicsSprite;
import android.pixelface.engine.geom.Vector2f;

public class GatherLocationFlockRule extends AbstractFlockingRule {

	IPositionable pos;
	private static final Vector2f vect = new Vector2f(0, 0);

	public GatherLocationFlockRule(GameEntityGroup<IPhysicsSprite> group,
			IPositionable pos, float factor) {
		super(group, factor);
		this.pos = pos;
	}

	@Override
	public Vector2f getRule(IPhysicsSprite p) {
		vect.setX(pos.getX() - p.getX());
		vect.setY(pos.getY() - p.getY());
		return vect;
	}

	@Override
	public void update(long time) {

	}
}
