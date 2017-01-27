package tp.pr5.mv.ins;

import java.io.IOException;

import tp.pr5.mv.IO.InStrategy;
import tp.pr5.mv.IO.OutStrategy;
import tp.pr5.mv.cpu.ExecuteManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exception.ExceptionDivisionByZero;
import tp.pr5.mv.exception.ExceptionMemoryNull;
import tp.pr5.mv.exception.ExceptionStack;

/**
 * 
 * @author usuario_local
 * 
 */

public interface Instruction {

	public abstract void execute(Memory<Integer> memoria, OperandStack<Integer> pila,
			ExecuteManager programa, InStrategy in, OutStrategy out)
			throws ExceptionStack, ExceptionDivisionByZero, IOException,
			ExceptionMemoryNull;

	public abstract Instruction parseIns(String cadena);

}
