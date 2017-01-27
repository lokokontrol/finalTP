package tp.pr5.mv.booleanas;

import tp.pr5.mv.ins.Instruction;

public class And extends Booleanas {

	public And() {

	}

	@Override
	// Conjuncion
	protected boolean operacion(int a, int b) {
		boolean conjuncion;
		if ((a != 0 && b != 0))
			conjuncion = true;
		else
			conjuncion = false;
		return conjuncion;
	}

	public Instruction parseIns(String cadena) {
		String[] t = cadena.split(" ");
		boolean correcto = false;
		Instruction ins;

		if (t[0].equalsIgnoreCase("and") && (t.length == 1))
			correcto = true;

		if (correcto == true)
			ins = new And();
		else
			ins = null;

		return ins;
	}

	@Override
	public String toString() {
		return "AND";

	}

}