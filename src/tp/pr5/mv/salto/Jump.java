package tp.pr5.mv.salto;

import tp.pr5.mv.IO.InStrategy;
import tp.pr5.mv.IO.OutStrategy;
import tp.pr5.mv.cpu.ExecuteManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.ins.InstructionParser;

public class Jump implements Instruction {

	private int argumento;

	public Jump(int argumento) {
		this.argumento = argumento;

	}

	public void execute(Memory<Integer> memoria, OperandStack<Integer> pila,
			ExecuteManager gestor, InStrategy in, OutStrategy out) {

		if (argumento == gestor.getCurrentPC()) {
			gestor.setNextPC(argumento - 1);
			gestor.setCurrentPC(argumento - 1);
		} else
			gestor.setNextPC(argumento);
	}

	public Instruction parseIns(String cadena) {
		String[] t = cadena.split(" ");
		boolean correcto = false;
		Instruction ins;

		if (t[0].equalsIgnoreCase("jump") && (t.length == 2)
				&& InstructionParser.esNumerico(t[1])
				&& Integer.parseInt(t[1]) > -1)
			correcto = true;

		if (correcto == true)
			ins = new Jump(Integer.parseInt(t[1]));
		else
			ins = null;

		return ins;

	}

	public String toString() {
		return "JUMP " + argumento;
	}

}
