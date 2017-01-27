package tp.pr5.mv.salto;

import tp.pr5.mv.cpu.ExecuteManager;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.ins.InstructionParser;

public class Rbt extends SaltoCondicional {

	private int argumento;

	public Rbt(int argumento) {
		this.argumento = argumento;
	}

	public boolean salta(boolean cierto, ExecuteManager gestor) {
		int next = gestor.getNextPC();

		if (cierto)
			if (argumento == 0) {
				gestor.setNextPC(argumento + next - 1);
				gestor.setCurrentPC(argumento + next - 1);
			} else
				gestor.setNextPC(argumento + next);

		return true;

	}

	public Instruction parseIns(String cadena) {
		String[] t = cadena.split(" ");
		boolean correcto = false;
		Instruction ins;

		if (t[0].equalsIgnoreCase("rbt") && (t.length == 2)
				&& InstructionParser.esNumerico(t[1]))
			correcto = true;

		if (correcto == true)
			ins = new Rbt(Integer.parseInt(t[1]));
		else
			ins = null;

		return ins;

	}

	public String toString() {
		return "RBT " + argumento;

	}
}
