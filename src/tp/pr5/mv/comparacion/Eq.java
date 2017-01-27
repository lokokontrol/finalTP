package tp.pr5.mv.comparacion;

import tp.pr5.mv.ins.Instruction;

public class Eq extends Comparacion {

	public Eq() {

	}

	@Override
	protected boolean comparacion(int n1, int n2) {
		boolean aux = false;
		if (n2 == n1)
			aux = true;
		return aux;
	}

	public Instruction parseIns(String cadena) {
		String[] t = cadena.split(" ");
		boolean correcto = false;
		Instruction ins;

		if (t[0].equalsIgnoreCase("eq") && (t.length == 1))
			correcto = true;

		if (correcto == true)
			ins = new Eq();
		else
			ins = null;

		return ins;
	}

	@Override
	public String toString() {
		return "EQ";

	}
}
