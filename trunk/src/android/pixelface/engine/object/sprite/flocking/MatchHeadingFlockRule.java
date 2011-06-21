package android.pixelface.engine.object.sprite.flocking;

import android.pixelface.engine.entity.GameEntityGroup;
import android.pixelface.engine.entity.sprite.IPhysicsSprite;
import android.pixelface.engine.geom.Vector2f;

public class MatchHeadingFlockRule extends AbstractFlockingRule {

	private final Vector2f sumVelocities = new Vector2f();
	private final Vector2f temp = new Vector2f();

	public MatchHeadingFlockRule(GameEntityGroup<IPhysicsSprite> group, float factor) {
		super(group, factor);
	}

	@Override
	public Vector2f getRule(IPhysicsSprite p) {
		sumVelocities.subtraction(p.getVelocityX(), p.getVelocityY());
		temp.setX(sumVelocities.getX());
		temp.setY(sumVelocities.getY());
		sumVelocities.addition(p.getVelocityX(), p.getVelocityY());
		return temp;
	}

	@Override
	public void update(long time) {
		sumVelocities.setX(0);
		sumVelocities.setY(0);
		GameEntityGroup<IPhysicsSprite> group = this.getGroup();
		for (int i = 0; i < group.size(); i++) {
			IPhysicsSprite sprite = group.get(i);
			sumVelocities
					.addition(sprite.getVelocityX(), sprite.getVelocityY());
		}
	}
}
