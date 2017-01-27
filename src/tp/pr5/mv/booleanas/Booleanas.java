package tp.pr5.mv.booleanas;

import tp.pr5.mv.IO.InStrategy;
import tp.pr5.mv.IO.OutStrategy;
import tp.pr5.mv.cpu.ExecuteManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exception.ExceptionStack;
import tp.pr5.mv.ins.Instruction;

public abstract class Booleanas implements Instruction {

	protected abstract boolean operacion(int a, int b);

	public void execute(Memory<Integer> memoria, OperandStack<Integer> pila,
			ExecuteManager gestor, InStrategy in, OutStrategy out)
			throws ExceptionStack {
		if (pila.getCima() < 2)
			throw new ExceptionStack("Error en la instruccion "
					+ this.toString() + " No hay operandos suficientes");
		else {
			int n1 = pila.pop();
			int n2 = pila.pop();
			if (operacion(n1, n2))
				pila.push(1);
			else
				pila.push(0);
		}

	}

}
