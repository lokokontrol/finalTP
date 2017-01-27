package tp.pr5.mv.salto;

import tp.pr5.mv.IO.InStrategy;
import tp.pr5.mv.IO.OutStrategy;
import tp.pr5.mv.cpu.ExecuteManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exception.ExceptionStack;
import tp.pr5.mv.ins.Instruction;

public abstract class SaltoCondicional implements Instruction {

	public abstract boolean salta(boolean cierto, ExecuteManager programa);

	public void execute(Memory<Integer> memoria, OperandStack<Integer> pila,
			ExecuteManager programa, InStrategy in, OutStrategy out)
			throws ExceptionStack {

		boolean cierto;

		int valorCima;

		if (pila.getCima() < 1)
			throw new ExceptionStack("No hay operandos suficientes");
		else {
			valorCima = pila.pop();

			if (valorCima == 0)
				cierto = false;
			else
				cierto = true;

			salta(cierto, programa);
		}

	}

}
