package tp.pr5.mv.Observable;

public interface MemoryObserver<T> {

	public void onWrite(int index, T value);
	public void onMemReset();

}
