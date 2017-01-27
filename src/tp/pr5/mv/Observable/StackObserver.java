package tp.pr5.mv.Observable;

public interface StackObserver<T> {
	public void onPush(T value);
	public void onPop(T value);
	
	// OPCIONAL
	public void onStackReset(); 

}
