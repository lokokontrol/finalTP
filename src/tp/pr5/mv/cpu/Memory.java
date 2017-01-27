package tp.pr5.mv.cpu;

import java.util.ArrayList;
import java.util.TreeMap;

import tp.pr5.mv.Observable.MemoryObserver;
import tp.pr5.mv.Observable.Observable;


public class Memory<T> implements  Observable<MemoryObserver<T>>{
	
	private TreeMap<Integer, T> memoria;
	private ArrayList<MemoryObserver<T>> observers;
	
	public static class MemCell<T>{
		int pos;
		T value;
		
		public MemCell(int pos, T value){
			this.pos = pos;
			this.value = value;
		}
		
		public int getPos (){
			return pos;
		}
		
		public T getValue (){
			return value;
		}
		
		public void setValue(T value){
			this.value = value;
		}
		
	}
	
	public ArrayList<MemCell<T>> getArray(){
		
	
		ArrayList<MemCell<T>> arrayAux = new ArrayList<MemCell<T>>();
		
		
		for ( Integer pos : memoria.keySet() ) {
				arrayAux.add( new MemCell<T>(pos, memoria.get(pos)));	
		}
		return arrayAux;
		
	}

	/**
	 * Constructor. Usamos la clase Constantes para indicar el tama�o del array
	 */
	public Memory() {
		memoria = new TreeMap<Integer, T>();
		observers = new ArrayList<MemoryObserver<T>>();
	}

	/**
	 * M�todo que devuelve el dato de memoria de la posicion pos. Si la posicion
	 * de memoria esta vacia, devuelve null.
	 * 
	 * @param pos
	 *            Posicion de la que queremos obtener el dato
	 * @return Devuelve el dato
	 */
	public T getValue(int pos) {	
		return memoria.get(pos);
		
	}

	/**
	 * M�todo que mete un valor en la posicion pos, de memoria.
	 * 
	 * @param pos
	 *            Posicion en la que queremos introducir un valor.
	 * @param value
	 *            Valor que queremos instroducir
	 */
	public void setValue(int pos, T value) { 
		memoria.put(pos, value);
		for (MemoryObserver<T> c: observers)
			c.onWrite(pos, value);
	}
	
	
	public void reset(){
		memoria.clear();
		for (MemoryObserver<T> c: observers)
			c.onMemReset();
	}
	

	/**
	 * M�todo que devuelve un String con la informaci�n de la memoria.
	 */
	public String toString() {
		String s = "";
		ArrayList<MemCell<T>> newMemory = getArray();
		for (int i = 0; i < newMemory.size(); i++) {
			if(newMemory.get(i)==null);
					
				s = s + "[" + newMemory.get(i).getPos() + "]" + ":" + newMemory.get(i).getValue() + " ";
		}
		if (s == "")
			s = "<vacia>";
		return s;
	}

	@Override
	public void addObserver(MemoryObserver<T> o) {
		observers.add(o);
		
	}

	@Override
	public void removeObserver(MemoryObserver<T> o) {
		observers.remove(o);
	}

}
