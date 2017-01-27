package tp.pr5.mv.otras;

import tp.pr5.mv.IO.InStrategy;
import tp.pr5.mv.IO.OutStrategy;
import tp.pr5.mv.cpu.ExecuteManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exception.ExceptionMemoryNull;
import tp.pr5.mv.exception.ExceptionStack;
import tp.pr5.mv.ins.Instruction;

public class LoadInd implements Instruction {

	public LoadInd() {

	}

	@Override
	public void execute(Memory<Integer> memoria, OperandStack<Integer> pila,
			ExecuteManager gestor, InStrategy in, OutStrategy out)
			throws ExceptionMemoryNull, ExceptionStack {
		if (pila.getCima() == 0)
			throw new ExceptionStack("Error en la instruccion "
					+ this.toString() + " No hay operandos suficientes");
		else {
			int dato = pila.pop();
			Integer aux = memoria.getValue(dato);
			if (aux == null)
				throw new ExceptionMemoryNull(" Error en la instruccion "
						+ this.toString() + " Direccion de memoria no valida");
			else
				pila.push(aux);

		}
	}

	@Override
	public Instruction parseIns(String cadena) {
		String[] t = cadena.split(" ");
		boolean correcto = false;
		Instruction ins;

		if (t[0].equalsIgnoreCase("loadind") && (t.length == 1))
			correcto = true;

		if (correcto == true)
			ins = new LoadInd();
		else
			ins = null;

		return ins;

	}

	public String toString() {
		return "LOADIND ";
	}

}
