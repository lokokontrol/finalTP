package tp.pr5.mv.booleanas;

import tp.pr5.mv.ins.Instruction;

public class Or extends Booleanas {

	public Or() {

	}

	@Override
	// Disyuncion
	protected boolean operacion(int a, int b) {
		boolean disyuncion;
		if ((a != 0 && b != 0) || (a == 0 && b != 0) || (a != 0 && b == 0))
			disyuncion = true;
		else
			disyuncion = false;
		return disyuncion;
	}

	public Instruction parseIns(String cadena) {
		String[] t = cadena.split(" ");
		boolean correcto = false;
		Instruction ins;

		if (t[0].equalsIgnoreCase("or") && (t.length == 1))
			correcto = true;

		if (correcto == true)
			ins = new Or();
		else
			ins = null;

		return ins;
	}

	@Override
	public String toString() {
		return "OR";

	}
}
