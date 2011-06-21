package android.pixelface.engine.entity;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.pixelface.engine.entity.sprite.ISprite;

public class DefaultGameEntityGroup<T extends ISprite> implements
		GameEntityGroup<T> {
	List<T> sprites;

	public DefaultGameEntityGroup() {
		this.sprites = new ArrayList<T>();
	}

	@Override
	public void update(long time) {
		for (int i = 0; i < sprites.size(); i++) {
			this.sprites.get(i).update(time);
		}
	}

	@Override
	public boolean remove(T t) {
		return sprites.remove(t);
	}

	@Override
	public boolean add(T t) {
		return sprites.add(t);
	}

	@Override
	public T get(int i) {
		return sprites.get(i);
	}

	@Override
	public int size() {
		return sprites.size();
	}

	@Override
	public void render(Canvas renderer) {
		for (int i = 0; i < sprites.size(); i++) {
			sprites.get(i).render(renderer);
		}
	}

}
