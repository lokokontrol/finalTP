package tp.pr5.mv.aritmeticas;

import tp.pr5.mv.IO.InStrategy;
import tp.pr5.mv.IO.OutStrategy;
import tp.pr5.mv.cpu.ExecuteManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exception.ExceptionDivisionByZero;
import tp.pr5.mv.exception.ExceptionStack;
import tp.pr5.mv.ins.Instruction;

public class Div extends Aritmeticas {

	public Div() {

	}

	public void execute(Memory<Integer> memoria, OperandStack<Integer> pila,
			ExecuteManager gestor, InStrategy in, OutStrategy out)
			throws ExceptionDivisionByZero, ExceptionStack {
		if (pila.getCima() < 2)
			throw new ExceptionStack("Error en la instruccion "
					+ this.toString() + " No hay operandos suficientes");
		if (pila.extraeCima() == 0)
			throw new ExceptionDivisionByZero("Error en la instruccion "
					+ this.toString() + " Division por cero");
		else {
			int n1 = pila.pop();
			int n2 = pila.pop();
			pila.push(operacion(n1, n2));
		}
	}

	protected int operacion(int op1, int op2) {

		return op2 / op1;
	}

	public Instruction parseIns(String cadena) {
		String[] t = cadena.split(" ");
		boolean correcto = false;
		Instruction ins;

		if (t[0].equalsIgnoreCase("div") && (t.length == 1))
			correcto = true;

		if (correcto == true)
			ins = new Div();
		else
			ins = null;

		return ins;

	}

	@Override
	public String toString() {
		return "DIV";
	}

}
