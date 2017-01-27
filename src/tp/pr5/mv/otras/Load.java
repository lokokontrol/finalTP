package tp.pr5.mv.otras;

import tp.pr5.mv.IO.InStrategy;
import tp.pr5.mv.IO.OutStrategy;
import tp.pr5.mv.cpu.ExecuteManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exception.ExceptionMemoryNull;
import tp.pr5.mv.ins.*;

public class Load implements Instruction {

	private int argumento;

	public Load(int arg) {
		this.argumento = arg;
	}

	@Override
	public void execute(Memory<Integer> memoria, OperandStack<Integer> pila,
			ExecuteManager programa, InStrategy in, OutStrategy out)
			throws ExceptionMemoryNull {
		Integer aux = memoria.getValue(argumento);
		if (aux == null)
			throw new ExceptionMemoryNull(" Error en la instruccion "
					+ this.toString() + " Direccion de memoria no valida "
					+ "(" + this.argumento + ")");
		else
			pila.push(aux);

	}

	public Instruction parseIns(String cadena) {
		String[] t = cadena.split(" ");
		boolean correcto = false;
		Instruction ins;

		if (t[0].equalsIgnoreCase("load") && (t.length == 2)
				&& InstructionParser.esNumerico(t[1]))
				//&& Integer.parseInt(t[1]) > -1)
			correcto = true;

		if (correcto == true)
			ins = new Load(Integer.parseInt(t[1]));
		else
			ins = null;

		return ins;

	}

	@Override
	public String toString() {
		return "LOAD " + argumento;
	}

}
