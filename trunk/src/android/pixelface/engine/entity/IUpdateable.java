package android.pixelface.engine.entity;

/**
 * Any entity needing updating implements this interface.
 * 
 * @author Will Morrison
 * 
 */
public interface IUpdateable {
	/**
	 * Updates this entity.
	 * 
	 * @param time
	 *            time elapsed since previous update.
	 */
	public void update(long time);
}
