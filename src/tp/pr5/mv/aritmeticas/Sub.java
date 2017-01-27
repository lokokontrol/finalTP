package tp.pr5.mv.aritmeticas;

import tp.pr5.mv.ins.Instruction;

public class Sub extends Aritmeticas {

	public Sub() {

	}

	protected int operacion(int op1, int op2) {

		return op2 - op1;
	}

	public Instruction parseIns(String cadena) {
		String[] t = cadena.split(" ");
		boolean correcto = false;
		Instruction ins;

		if (t[0].equalsIgnoreCase("sub") && (t.length == 1))
			correcto = true;

		if (correcto == true)
			ins = new Sub();
		else
			ins = null;

		return ins;

	}

	@Override
	public String toString() {
		return "SUB";
	}

}
