package tp.pr5.mv.aritmeticas;

import tp.pr5.mv.IO.InStrategy;
import tp.pr5.mv.IO.OutStrategy;
import tp.pr5.mv.cpu.ExecuteManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exception.ExceptionDivisionByZero;
import tp.pr5.mv.exception.ExceptionStack;
import tp.pr5.mv.ins.Instruction;

public abstract class Aritmeticas implements Instruction {

	protected abstract int operacion(int a, int b);

	public void execute(Memory<Integer> memoria, OperandStack<Integer> pila, ExecuteManager gestor, InStrategy in, OutStrategy out)
			throws ExceptionStack, ExceptionDivisionByZero {
		if (pila.getCima() < 2)
			throw new ExceptionStack("Error en la instruccion "
					+ this.toString() + " No hay operandos suficientes");
		else {
			int n1 = pila.pop();
			int n2 = pila.pop();
			pila.push(operacion(n1, n2));
		}

	}

}