package tp.pr5.mv.otras;

import tp.pr5.mv.IO.InStrategy;
import tp.pr5.mv.IO.OutStrategy;
import tp.pr5.mv.cpu.ExecuteManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.ins.InstructionParser;

public class Push implements Instruction {

	private int argumento;

	public Push(int arg) {
		this.argumento = arg;
	}

	@Override
	public void execute(Memory<Integer> memoria, OperandStack<Integer> pila,
			ExecuteManager programa, InStrategy in, OutStrategy out) {
		pila.push(argumento);
	}

	public Instruction parseIns(String cadena) {
		String[] t = cadena.split(" ");
		boolean correcto = false;
		Instruction ins;

		if (t[0].equalsIgnoreCase("push") && (t.length == 2)
				&& InstructionParser.esNumerico(t[1]))
			correcto = true;

		if (correcto == true)
			ins = new Push(Integer.parseInt(t[1]));
		else
			ins = null;

		return ins;

	}

	@Override
	public String toString() {
		return "PUSH " + argumento;
	}

}
