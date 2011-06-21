package android.pixelface.engine.object.sprite.flocking;

import android.pixelface.engine.entity.GameEntityGroup;
import android.pixelface.engine.entity.sprite.IPhysicsSprite;
import android.pixelface.engine.geom.Vector2f;

public class VelocityMatchFlockRule extends AbstractFlockingRule {
	private final Vector2f sumVelocity = new Vector2f();
	private final Vector2f temp = new Vector2f();

	public VelocityMatchFlockRule(GameEntityGroup<IPhysicsSprite> group,
			float factor) {
		super(group, factor);
	}

	@Override
	public Vector2f getRule(IPhysicsSprite p) {
		sumVelocity.subtraction(p.getVelocityX(), p.getVelocityY());
		int size = this.getGroup().size() - 1;
		temp.setX(sumVelocity.getX() / size);
		temp.setY(sumVelocity.getY() / size);
		sumVelocity.addition(p.getVelocityX(), p.getVelocityY());
		return temp;
	}

	@Override
	public void update(long time) {
		sumVelocity.setX(0);
		sumVelocity.setY(0);
		GameEntityGroup<IPhysicsSprite> group = this.getGroup();
		IPhysicsSprite temp;
		for (int i = 0; i < group.size(); i++) {
			temp = group.get(i);
			sumVelocity.addition(temp.getVelocityX(), temp.getVelocityY());
		}
	}
}
