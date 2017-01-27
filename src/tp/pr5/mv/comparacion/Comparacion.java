package tp.pr5.mv.comparacion;

import tp.pr5.mv.IO.InStrategy;
import tp.pr5.mv.IO.OutStrategy;
import tp.pr5.mv.cpu.ExecuteManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exception.ExceptionDivisionByZero;
import tp.pr5.mv.exception.ExceptionStack;
import tp.pr5.mv.ins.Instruction;

public abstract class Comparacion implements Instruction {

	protected abstract boolean comparacion(int a, int b);

	public void execute(Memory<Integer> memoria, OperandStack<Integer> pila,
			ExecuteManager programa, InStrategy in, OutStrategy out)
			throws ExceptionStack, ExceptionDivisionByZero {

		if (pila.getCima() < 2)
			throw new ExceptionStack("No hay suficientes operandos");
		else {
			int n1 = pila.pop();
			int n2 = pila.pop();
			if (comparacion(n1, n2))
				pila.push(1);
			else
				pila.push(0);
		}

	}

}
