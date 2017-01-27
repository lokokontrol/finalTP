package tp.pr5.mv.cpu;

import java.io.IOException;
import java.util.ArrayList;

import tp.pr5.mv.AAA.main.Constantes;
import tp.pr5.mv.IO.InStrategy;
import tp.pr5.mv.IO.OutStrategy;
import tp.pr5.mv.Observable.CPUObserver;
import tp.pr5.mv.Observable.Observable;
import tp.pr5.mv.exception.ExceptionDivisionByZero;
import tp.pr5.mv.exception.ExceptionMemoryNull;
import tp.pr5.mv.exception.ExceptionStack;
import tp.pr5.mv.exception.MVError;
import tp.pr5.mv.ins.Instruction;

public class CPU implements Observable<CPUObserver> {

	private Memory<Integer> memoria;
	private OperandStack<Integer> pila;
	private ProgramMV<Instruction> programa;
	private ExecuteManager gestor;
	
	private InStrategy in;
	private OutStrategy out;
	private ArrayList<CPUObserver> observers; 
	
	private int delay = 0;
	
	
	public CPU() {
		memoria = new Memory<Integer>();
		pila = new OperandStack<Integer>();
		gestor = new ExecuteManager();
		programa = new ProgramMV<Instruction>();
		observers = new ArrayList<CPUObserver>();
	}

	public void loadProgram(ProgramMV<Instruction> prog) {
		this.programa = prog;
	}

	public ProgramMV<Instruction> getPrograma() {
		return programa;
	}
	
	public OperandStack<Integer> getPila(){
		return pila;
	}
	
	public int getPC(){
		return gestor.getCurrentPC();
	}
	
	public Memory<Integer> getMemory(){
		return memoria;
	}

	public void step() throws ExceptionStack, ExceptionDivisionByZero,
			IOException, ExceptionMemoryNull {

		int instActual = gestor.getCurrentPC();
		int longProgram = programa.getContador();
		Instruction ins;
		
		if ((instActual < longProgram) && instActual >= 0) {

			ins = programa.getInstruction(instActual);
			try{
				ins.execute(memoria, pila, gestor, in, out);
			}catch(ExceptionStack | ExceptionDivisionByZero | IOException | ExceptionMemoryNull e){
				for (CPUObserver c : observers){
					c.onError(e.getMessage());
					c.onEndRun();
				}
					
				throw e;
			}
			for (CPUObserver c : observers){
				c.onStartInstrExecution(ins);
				
			}
		}

		gestor.incrementPC();
		if (gestor.getCurrentPC() > longProgram)
			gestor.setHalt(true);
		
		for (CPUObserver c : observers)
			c.onEndInstrExecution(gestor.getCurrentPC(), memoria, pila);
	}

	/**
	 * Devuelve true si la maquina esta parada La maquina esta parada si ejecuto
	 * la instruccion HALT o el contador de programa alcanzo una instruccion que
	 * no existe
	 */
	public boolean isHalted() {
		boolean parada = false;

		if (gestor.getHalt() == true || gestor.getCurrentPC() == programa.getContador()) {
			parada = true;
		}
		return parada;
	}

	public String toStringInicio() {
		String s = "Comienza la ejecucion de "
				+ programa.getInstruction(gestor.getCurrentPC()).toString();
		return s;
	}

	@Override
	public String toString() {

		String s = "El estado de la maquina tras ejecutar la instruccion es: "
				+ Constantes.Salto + "Memoria: " + memoria.toString()
				+ Constantes.Salto + "Pila de operandos: " + pila.toString();
		return s;
	}

	public void setInStrategy(InStrategy s) throws MVError {
		if (s == null)
			throw new MVError("Cannot set inStream to null");
		else
			in = s;
	}

	public void setOutStrategy(OutStrategy s) throws MVError {
		if (s == null)
			throw new MVError("Cannot set inStream to null");
		else
			out = s;
	}

	public InStrategy getInStrategy() {
		return in;
	}

	public OutStrategy getOutStrategy() {
		return out;
	}
	
	public void run() throws ExceptionStack, ExceptionDivisionByZero, IOException, ExceptionMemoryNull, InterruptedException{
		
		for (CPUObserver c : observers)
			c.onStartRun();
		gestor.setHalt(false);
		while (!this.isHalted()) {
			this.step();
			Thread.sleep(delay);
		}
		 if (!gestor.getHalt()==true){
			 for (CPUObserver c: observers)
			 c.onEndRun();
		 }
		 else
			 for (CPUObserver c: observers)
				 c.onHalt();


	}

	@Override
	public void addObserver(CPUObserver o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(CPUObserver o) {
		observers.remove(o);
	}
	
	public void Push(int valor){
		pila.push(valor);
	}
	
	public void Pop() throws ExceptionStack{
		try {
			pila.pop();
		} catch (ExceptionStack e) {
			for (CPUObserver c : observers)
				c.onError(e.getMessage());
			throw e;
		}
		
	}
	
	public void Write(int index,int value){
		memoria.setValue(index, value);
	}
	
	public void Steps(int numero) throws ExceptionStack, ExceptionDivisionByZero, IOException, ExceptionMemoryNull{
		int i = 0;
		while (i < numero && this.isHalted() == false) {
			this.step();
			i++;
		}
	}
	
	public void reset(){
		pila.reset();
		memoria.reset();
		gestor.reset();
		in.reset();
		out.reset();
		for (CPUObserver c: observers)
			c.onReset(programa);
	}

	public void pause() {
		gestor.setHalt(true);
	}
	
	public void setDelay(int n){
		this.delay=n;
	}

	public void cargarNuevoFichero(ProgramMV<Instruction> newProg) {
		this.loadProgram(newProg);
		pila.reset();
		memoria.reset();
		gestor.reset();
		in.reset();
		out.reset();
		for (CPUObserver c: observers)
			c.onReset(newProg);
	}
		
}
