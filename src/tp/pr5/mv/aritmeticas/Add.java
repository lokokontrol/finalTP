package tp.pr5.mv.aritmeticas;

import tp.pr5.mv.ins.Instruction;

public class Add extends Aritmeticas {

	public Add() {

	}

	protected int operacion(int op1, int op2) {

		return op1 + op2;
	}

	@Override
	public String toString() {
		return "ADD";
	}

	public Instruction parseIns(String cadena) {
		String[] t = cadena.split(" ");
		boolean correcto = false;
		Instruction ins;

		if (t[0].equalsIgnoreCase("add") && (t.length == 1))
			correcto = true;

		if (correcto == true)
			ins = new Add();
		else
			ins = null;

		return ins;

	}

}
