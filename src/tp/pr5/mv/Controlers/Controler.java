package tp.pr5.mv.Controlers;

import java.io.IOException;

import tp.pr5.mv.IO.InStrategy;
import tp.pr5.mv.IO.OutStrategy;
import tp.pr5.mv.cpu.CPU;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.exception.ExceptionDivisionByZero;
import tp.pr5.mv.exception.ExceptionInsIncorrecta;
import tp.pr5.mv.exception.ExceptionMemoryNull;
import tp.pr5.mv.exception.ExceptionStack;
import tp.pr5.mv.exception.MVError;
import tp.pr5.mv.ins.Instruction;

public abstract class Controler {
	private CPU cpu;
	
	public Controler(CPU cpu) {
		this.cpu = cpu;
	}
	
	public void step(){ 
		try {
			cpu.step();
		} catch (ExceptionStack | ExceptionDivisionByZero | IOException	| ExceptionMemoryNull e) {}
	}
	
	public void steps(int numero){
		try {
			cpu.Steps(numero);
		} catch (ExceptionStack | ExceptionDivisionByZero | IOException	| ExceptionMemoryNull e) {}
	}
	
	
	public void run(){ 
		try {
			cpu.run();
		} catch (ExceptionStack | ExceptionDivisionByZero | IOException| ExceptionMemoryNull  | InterruptedException e) {}
	}
	
	public void pop(){ 
		try {
			cpu.Pop();
		} catch (ExceptionStack e) {}
			
	}
	
	public void push(int valor) {
		try {
			cpu.Push(valor);
		} catch (NumberFormatException e) {}
	}
	
	public void write(int pos, int value){
		cpu.Write(pos, value);
	}
	
	public ProgramMV<Instruction> getProgram() {
		return cpu.getPrograma(); 
	} 
	
	public void setInStrategy(InStrategy in) { 
		try {
			cpu.setInStrategy(in);
		} catch (MVError e) {}
	}
	
	public InStrategy getInStrategy() {
		return cpu.getInStrategy();
	}
	
	public void setOutStrategy(OutStrategy out) { 
		try {
			cpu.setOutStrategy(out);
		} catch (MVError e) {}
	}
	
	public OutStrategy getOutStrategy() {
		return cpu.getOutStrategy();
	}
	
	public void pause(){
		cpu.pause();
	};
	
	public abstract void start() throws IOException;
	
	public void quit() {
			try {
				cpu.getInStrategy().close();
				cpu.getOutStrategy().close();
			} catch (IOException e) {}
			finally{ System.exit(0);}
			
	}
	
	public int getPC() { 
		return cpu.getPC(); 
	}
	
	public OperandStack<Integer> getOperandStack() {
		return cpu.getPila(); 
	}
	
	public Memory<Integer> getMemory() {
		return cpu.getMemory();
	}

	public void reset(){
		cpu.reset();
	}
	
	public void cargarFichero(String nombrePrograma) {
		ProgramMV<Instruction> newProg;
		try {
			newProg = ProgramMV.CargarParsearDeFichero(nombrePrograma);
			cpu.cargarNuevoFichero(newProg);
		} catch (ExceptionInsIncorrecta e) {}
		
	}
	

	
	
	
}

