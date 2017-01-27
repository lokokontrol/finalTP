package tp.pr5.mv.otras;

import tp.pr5.mv.IO.InStrategy;
import tp.pr5.mv.IO.OutStrategy;
import tp.pr5.mv.aritmeticas.Sub;
import tp.pr5.mv.cpu.ExecuteManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exception.ExceptionDivisionByZero;
import tp.pr5.mv.exception.ExceptionStack;
import tp.pr5.mv.ins.*;

public class Neg implements Instruction {

	public Neg() {

	}

	@Override
	public void execute(Memory<Integer> memoria, OperandStack<Integer> pila,
			ExecuteManager programa, InStrategy in, OutStrategy out)
			throws ExceptionStack, ExceptionDivisionByZero {
		if (pila.getCima() < 1)
			throw new ExceptionStack("Error en la instruccion "
					+ this.toString() + " No hay operandos suficientes");
		else {
			pila.push(0);
			Flip aux = new Flip();
			aux.execute(memoria, pila, programa, null, null);
			Sub aux2 = new Sub();
			aux2.execute(memoria, pila, programa, null, null);
		}

	}

	public Instruction parseIns(String cadena) {
		String[] t = cadena.split(" ");
		boolean correcto = false;
		Instruction ins;

		if (t[0].equalsIgnoreCase("neg") && (t.length == 1))
			correcto = true;

		if (correcto == true)
			ins = new Neg();
		else
			ins = null;

		return ins;

	}

	@Override
	public String toString() {
		return "NEG";
	}

}
