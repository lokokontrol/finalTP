package tp.pr5.mv.salto;

import tp.pr5.mv.IO.InStrategy;
import tp.pr5.mv.IO.OutStrategy;
import tp.pr5.mv.cpu.ExecuteManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.ins.InstructionParser;

public class Rjump implements Instruction {
	private int argumento;

	public Rjump(int argumento) {
		this.argumento = argumento;
	}

	public void execute(Memory<Integer> memoria, OperandStack<Integer> pila,
			ExecuteManager gestor, InStrategy in, OutStrategy out) {
		int next = gestor.getNextPC();
		if (argumento == 0) {
			gestor.setNextPC(argumento + next - 1);
			gestor.setCurrentPC(argumento + next - 1);
		}
		gestor.setNextPC(argumento + next);
	}

	public Instruction parseIns(String cadena) {
		String[] t = cadena.split(" ");
		boolean correcto = false;
		Instruction ins;

		if (t[0].equalsIgnoreCase("rjump") && (t.length == 2)
				&& InstructionParser.esNumerico(t[1]))
			correcto = true;

		if (correcto == true)
			ins = new Rjump(Integer.parseInt(t[1]));
		else
			ins = null;

		return ins;

	}

	public String toString() {
		return "RJUMP " + argumento;

	}

}
