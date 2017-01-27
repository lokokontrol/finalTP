package tp.pr5.mv.otras;

import tp.pr5.mv.IO.InStrategy;
import tp.pr5.mv.IO.OutStrategy;
import tp.pr5.mv.cpu.ExecuteManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.ins.Instruction;

public class Halt implements Instruction {

	public Halt() {

	}

	@Override
	public void execute(Memory<Integer> memoria, OperandStack<Integer> pila,
			ExecuteManager gestor, InStrategy in, OutStrategy out) {
		boolean sePulsoHalt = true;
		gestor.setHalt(sePulsoHalt);
	}

	public Instruction parseIns(String cadena) {
		String[] t = cadena.split(" ");
		boolean correcto = false;
		Instruction ins;

		if (t[0].equalsIgnoreCase("halt") && (t.length == 1))
			correcto = true;

		if (correcto == true)
			ins = new Halt();
		else
			ins = null;

		return ins;

	}

	@Override
	public String toString() {
		return "HALT";
	}

}
