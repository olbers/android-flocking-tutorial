package android.pixelface.engine.entity;

public interface IGroup<T> {
	public boolean remove(T t);

	public boolean add(T t);

	public T get(int i);

	public int size();
}
