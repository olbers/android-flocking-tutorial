package android.pixelface.engine.entity.sprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class SpriteSheet {
	private int tileWidth;
	private int tileHeight;
	private int tileMargin;
	private int tileSpacing;
	private Bitmap bitmap;
	private final Rect srcRect = new Rect();
	private final Rect dstRect = new Rect();

	public SpriteSheet(Bitmap bitmap, int tileWidth, int tileHeight,
			int tileSpacing, int tileMargin) {
		this.bitmap = bitmap;
		this.tileSpacing = tileSpacing;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		this.tileMargin = tileMargin;
	}

	public int getHorizontalCount() {
		return this.bitmap.getWidth() / tileWidth;
	}

	public int getVerticalCount() {
		return this.bitmap.getHeight() / tileHeight;
	}

	public void render(Canvas renderer, int x, int y, int tileX, int tileY) {
		dstRect.left = x;
		dstRect.top = y;
		dstRect.right = x + tileWidth;
		this.dstRect.bottom = y + tileHeight;
		int tileSpacing2 = tileSpacing << 1;
		this.srcRect.left = tileX * (tileWidth + tileSpacing2) + tileSpacing;
		this.srcRect.top = tileY * (tileHeight + tileSpacing2) + tileSpacing;
		this.srcRect.right = srcRect.left + tileWidth;
		this.srcRect.bottom = srcRect.top + tileHeight;
		renderer.drawBitmap(bitmap, srcRect, dstRect, null);
	}
}
