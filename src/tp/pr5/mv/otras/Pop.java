package tp.pr5.mv.otras;

import tp.pr5.mv.IO.InStrategy;
import tp.pr5.mv.IO.OutStrategy;
import tp.pr5.mv.cpu.ExecuteManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exception.ExceptionStack;
import tp.pr5.mv.ins.Instruction;

public class Pop implements Instruction {

	public Pop() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Memory<Integer> memoria, OperandStack<Integer> pila,
			ExecuteManager programa, InStrategy in, OutStrategy out)
			throws ExceptionStack {
		if (pila.getCima() == 0)
			throw new ExceptionStack("Error en la instruccion "
					+ this.toString() + " No hay operandos suficientes");
		else
			pila.pop();

	}

	public Instruction parseIns(String cadena) {
		String[] t = cadena.split(" ");
		boolean correcto = false;
		Instruction ins;

		if (t[0].equalsIgnoreCase("pop") && (t.length == 1))
			correcto = true;

		if (correcto == true)
			ins = new Pop();
		else
			ins = null;

		return ins;

	}

	@Override
	public String toString() {
		return "POP";
	}

}
