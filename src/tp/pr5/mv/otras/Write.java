package tp.pr5.mv.otras;

import tp.pr5.mv.IO.InStrategy;
import tp.pr5.mv.IO.OutStrategy;
import tp.pr5.mv.cpu.ExecuteManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.ins.InstructionParser;

public class Write implements Instruction {

	private int argumento1;
	private int argumento2;

	public Write(int argumento1, int argumento2) {
		this.argumento1 = argumento1;
		this.argumento2 = argumento2;
	}

	@Override
	public void execute(Memory<Integer> memoria, OperandStack<Integer> pila,
			ExecuteManager gestor, InStrategy in, OutStrategy out) {
		memoria.setValue(argumento1, argumento2);
	}

	public String toString() {
		return "WRITE" + argumento1 + argumento2;
	}

	@Override
	public Instruction parseIns(String cadena) {
		String[] t = cadena.split(" ");
		boolean correcto = false;
		Instruction ins;

		if (t[0].equalsIgnoreCase("write") && (t.length == 3)
				&& InstructionParser.esNumerico(t[1])
				&& InstructionParser.esNumerico(t[2]))
			correcto = true;

		if (correcto == true)
			ins = new Write(Integer.parseInt(t[1]), Integer.parseInt(t[2]));
		else
			ins = null;

		return ins;

	}

}
