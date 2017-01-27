package tp.pr5.mv.salto;

import tp.pr5.mv.cpu.ExecuteManager;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.ins.InstructionParser;

public class Bf extends SaltoCondicional {

	private int argumento;

	public Bf(int argumento) {
		this.argumento = argumento;
	}

	/**
	 * Salta si el valor de la cima de la pila es false == 0
	 */
	public boolean salta(boolean cierto, ExecuteManager gestor) {

		if (cierto == false) {
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

		if (t[0].equalsIgnoreCase("bf") && (t.length == 2)
				&& InstructionParser.esNumerico(t[1])
				&& Integer.parseInt(t[1]) > -1)
			correcto = true;

		if (correcto == true)
			ins = new Bf(Integer.parseInt(t[1]));
		else
			ins = null;

		return ins;

	}

	public String toString() {
		return "BF " + argumento;

	}

}
