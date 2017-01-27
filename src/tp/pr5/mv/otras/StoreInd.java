package tp.pr5.mv.otras;

import tp.pr5.mv.IO.InStrategy;
import tp.pr5.mv.IO.OutStrategy;
import tp.pr5.mv.cpu.ExecuteManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exception.ExceptionStack;
import tp.pr5.mv.ins.Instruction;

public class StoreInd implements Instruction {

	/**
	 * Escribe en la posicion de memoria indicada por la subcima el valor que
	 * aparece en la cima
	 */
	@Override
	public void execute(Memory<Integer> memoria, OperandStack<Integer> pila,
			ExecuteManager gestor, InStrategy in, OutStrategy out)
			throws ExceptionStack {
		if (pila.getCima() < 2)
			throw new ExceptionStack("Error en la instruccion "
					+ this.toString() + " No hay operandos suficientes");
		else {
			int argumento = pila.pop();
			int posicion = pila.pop();
			memoria.setValue(posicion, argumento);
		}

	}

	@Override
	public Instruction parseIns(String cadena) {
		String[] t = cadena.split(" ");
		boolean correcto = false;

		Instruction ins;

		if (t[0].equalsIgnoreCase("storeind") && (t.length == 1))
			correcto = true;

		if (correcto == true) {
			ins = new StoreInd();

		} else {
			ins = null;
		}

		return ins;

	}

	public String toString() {

		return "STOREIND ";
	}

}
