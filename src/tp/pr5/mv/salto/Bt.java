package tp.pr5.mv.salto;

import tp.pr5.mv.cpu.ExecuteManager;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.ins.InstructionParser;

public class Bt extends SaltoCondicional {

	private int argumento;

	public Bt(int arg) {
		this.argumento = arg;
	}

	/**
	 * Salta si el valor de la cima de la pila es cierto
	 */

	public boolean salta(boolean cierto, ExecuteManager gestor) {

		if (cierto == true) {
			if (argumento == gestor.getCurrentPC()) {
				gestor.setNextPC(argumento - 1);
				gestor.setCurrentPC(argumento - 1);
			} else
				gestor.setNextPC(argumento);
		}
		return true;
	}

	public Instruction parseIns(String cadena) {
		String[] t = cadena.split(" ");
		boolean correcto = false;
		Instruction ins;

		if (t[0].equalsIgnoreCase("bt") && (t.length == 2)
				&& InstructionParser.esNumerico(t[1])
				&& Integer.parseInt(t[1]) > -1)
			correcto = true;

		if (correcto == true)
			ins = new Bt(Integer.parseInt(t[1]));
		else
			ins = null;

		return ins;

	}

	public String toString() {
		return "BT " + argumento;

	}

}
