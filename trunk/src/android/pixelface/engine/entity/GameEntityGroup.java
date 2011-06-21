package android.pixelface.engine.entity;

import android.pixelface.engine.entity.sprite.ISprite;

public interface GameEntityGroup<T extends ISprite> extends IUpdateable,
		IGroup<T>, IRenderable {

}
