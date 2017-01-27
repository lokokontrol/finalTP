package tp.pr5.mv.otras;

import tp.pr5.mv.IO.InStrategy;
import tp.pr5.mv.IO.OutStrategy;
import tp.pr5.mv.cpu.ExecuteManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exception.ExceptionStack;
import tp.pr5.mv.ins.*;

public class Store implements Instruction {

	private int argumento;

	public Store(int arg) {
		this.argumento = arg;
	}

	@Override
	public void execute(Memory<Integer> memoria, OperandStack<Integer> pila,
			ExecuteManager programa, InStrategy in, OutStrategy out)
			throws ExceptionStack {
		if (pila.getCima() == 0)
			throw new ExceptionStack(" Error en la instruccion "
					+ this.toString() + " No hay operandos suficientes");
		else
			memoria.setValue(argumento, pila.pop());
	}

	public Instruction parseIns(String cadena) {
		String[] t = cadena.split(" ");
		boolean correcto = false;
		Instruction ins;

		if (t[0].equalsIgnoreCase("store") && (t.length == 2)
				&& InstructionParser.esNumerico(t[1])
				&& Integer.parseInt(t[1]) > -1)
			correcto = true;

		if (correcto == true)
			ins = new Store(Integer.parseInt(t[1]));
		else
			ins = null;

		return ins;

	}

	@Override
	public String toString() {
		return "STORE " + argumento;
	}

}
