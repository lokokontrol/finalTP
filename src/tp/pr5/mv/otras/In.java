package tp.pr5.mv.otras;

import java.io.IOException;

import tp.pr5.mv.IO.InStrategy;
import tp.pr5.mv.IO.OutStrategy;
import tp.pr5.mv.cpu.ExecuteManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exception.ExceptionStack;
import tp.pr5.mv.ins.Instruction;

public class In implements Instruction {

	public In() {

	}

	@Override
	public void execute(Memory<Integer> memoria, OperandStack<Integer> pila,
			ExecuteManager programa, InStrategy in, OutStrategy out)
			throws ExceptionStack, IOException {
		pila.push(in.read());
	}

	public Instruction parseIns(String cadena) {
		String[] t = cadena.split(" ");
		boolean correcto = false;
		Instruction ins;

		if (t[0].equalsIgnoreCase("in") && (t.length == 1))
			correcto = true;

		if (correcto == true)
			ins = new In();
		else
			ins = null;

		return ins;

	}

	@Override
	public String toString() {
		return "IN";
	}

}
