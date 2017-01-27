package tp.pr5.mv.aritmeticas;

import tp.pr5.mv.ins.Instruction;

public class Mul extends Aritmeticas {

	public Mul() {

	}

	protected int operacion(int op1, int op2) {

		return op1 * op2;
	}

	public Instruction parseIns(String cadena) {
		String[] t = cadena.split(" ");
		boolean correcto = false;
		Instruction ins;

		if (t[0].equalsIgnoreCase("mul") && (t.length == 1))
			correcto = true;

		if (correcto == true)
			ins = new Mul();
		else
			ins = null;

		return ins;

	}

	@Override
	public String toString() {
		return "MUL";
	}

}
