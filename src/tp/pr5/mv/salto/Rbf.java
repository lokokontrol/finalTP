package tp.pr5.mv.salto;

import tp.pr5.mv.cpu.ExecuteManager;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.ins.InstructionParser;

public class Rbf extends SaltoCondicional {

	private int argumento;

	public Rbf(int argumento) {
		this.argumento = argumento;
	}

	@Override
	public boolean salta(boolean cierto, ExecuteManager gestor) {
		int next = gestor.getNextPC();

		if (cierto == false) {
			if (argumento == 0) {
				gestor.setNextPC(argumento + next - 1);
				gestor.setCurrentPC(argumento + next - 1);
			} else
				gestor.setNextPC(argumento + next);
		}
		return true;
	}

	public Instruction parseIns(String cadena) {
		String[] t = cadena.split(" ");
		boolean correcto = false;
		Instruction ins;

		if (t[0].equalsIgnoreCase("rbf") && (t.length == 2)
				&& InstructionParser.esNumerico(t[1]))
			correcto = true;

		if (correcto == true)
			ins = new Rbf(Integer.parseInt(t[1]));
		else
			ins = null;

		return ins;

	}

	public String toString() {
		return "RBF " + argumento;

	}

}
