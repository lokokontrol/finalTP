package tp.pr5.mv.Observable;

public interface Observable<T> {
	
	public void addObserver(T o);
	public void removeObserver(T o);

}
