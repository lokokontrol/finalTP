package tp.pr5.mv.comparacion;

import tp.pr5.mv.ins.Instruction;

public class Le extends Comparacion {

	public Le() {

	}

	@Override
	public String toString() {
		return "LE";

	}

	public Instruction parseIns(String cadena) {
		String[] t = cadena.split(" ");
		boolean correcto = false;
		Instruction ins;

		if (t[0].equalsIgnoreCase("le") && (t.length == 1))
			correcto = true;

		if (correcto == true)
			ins = new Le();
		else
			ins = null;

		return ins;
	}

	@Override
	protected boolean comparacion(int n1, int n2) {
		boolean aux = false;
		if (n2 <= n1)
			aux = true;
		return aux;
	}

}
