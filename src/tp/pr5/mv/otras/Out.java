package tp.pr5.mv.otras;

import java.io.IOException;

import tp.pr5.mv.IO.InStrategy;
import tp.pr5.mv.IO.OutStrategy;
import tp.pr5.mv.cpu.ExecuteManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exception.ExceptionStack;
import tp.pr5.mv.ins.*;

public class Out implements Instruction {

	public Out() {

	}

	@Override
	public void execute(Memory<Integer> memoria, OperandStack<Integer> pila,
			ExecuteManager programa, InStrategy in, OutStrategy out)
			throws ExceptionStack, IOException {
		if (pila.getCima() <= 0)
			throw new ExceptionStack("Error en la instruccion "
					+ this.toString() + " No hay operandos suficientes");
		else {
			int numero = pila.pop();
			out.write(numero);
		}
	}

	public Instruction parseIns(String cadena) {
		String[] t = cadena.split(" ");
		boolean correcto = false;
		Instruction ins;

		if (t[0].equalsIgnoreCase("out") && (t.length == 1))
			correcto = true;

		if (correcto == true)
			ins = new Out();
		else
			ins = null;

		return ins;

	}

	@Override
	public String toString() {
		return "OUT";
	}

}
