package tp.pr5.mv.cpu;

import java.util.ArrayList;
import tp.pr5.mv.Observable.Observable;
import tp.pr5.mv.Observable.StackObserver;
import tp.pr5.mv.exception.ExceptionStack;

public class OperandStack<T> implements Observable<StackObserver<T>> {

	private ArrayList<T> stack;
	private ArrayList<StackObserver<T>> observers;
	/**
	 * Constructor.
	 */
	public OperandStack() {

		this.stack = new ArrayList<T>();
		this.observers = new ArrayList<StackObserver<T>>();
	}

	/**
	 * 
	 * @return El valor de cima
	 */
	public int getCima() {

		// Devuelve la posici—n de la œltima ocurrencia ('Elemento') en el
		// ArrayList

		return stack.size();

	}

	/**
	 * Metodo que extrae el ultimo valor de la pila y a la vez lo elimina de la
	 * pila.
	 * 
	 * @return El ultimo valor de la pila.
	 * @throws ExceptionStack 
	 */
	public T pop() throws ExceptionStack {

		// Devolvemos la posicion de la cima de la pila
		Integer ultimaPos = stack.size();
		if (ultimaPos == 0)
			throw new ExceptionStack("Error en la ejecucion de "
					+ "POP" + " No hay operandos suficientes");
		else {
		// Devolvemos el valos de la cima de la pila
		T dato = stack.get(ultimaPos - 1);
		// Borramos la cima de la pila
		stack.remove(ultimaPos - 1);
		for (StackObserver<T> c: observers)
			c.onPop(dato);
			
		
		return dato;
		}
	}
	
	public void popSinDevolver(){
		int ultimaPos = stack.size();

		// Borramos la cima de la pila
		stack.remove(ultimaPos - 1);
	}

	/**
	 * Método para introducir valores en la pila
	 * 
	 * @param num
	 *            Número que queremos introducir en la pila.
	 */
	public void push(T num) {
		stack.add(num);
		for (StackObserver<T> c: observers)
			c.onPush(num);
	}

	/**
	 * Método que devuelve el ultimo valor de la pila sin eliminarlo
	 * 
	 * @return Último valor de la cima.
	 */
	public T extraeCima() {
		// Devolvemos la posicion de la cima de la pila
		int cima = stack.size();
		// Devolvemos el valor de la cima de la pila
		T valor = stack.get(cima - 1);

		return valor;
	}

	/**
	 * Metodo que devuelve el estado de la pila.
	 */
	public String toString() {

		String s = "";
		int cima = stack.size();

		if (stack.isEmpty()) {
			s = "<vacia>";
		} else
			for (int i = 0; i < cima; i++)
				s = s + stack.get(i) + " ";

		return s;

	}

	public ArrayList<T> getStack() {
		return stack;
	}
	
	public T dameDatoPila(int indice){
		return stack.get(indice);
	}

	public void setStack(ArrayList<T> stack) {
		this.stack = stack;
	}

	@Override
	public void addObserver(StackObserver<T> o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(StackObserver<T> o) {
		observers.remove(o);
	}
	
	public void reset(){
		stack.clear();
		for (StackObserver<T> c: observers)
			c.onStackReset();
	}

}
