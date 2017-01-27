package tp.pr5.mv.salto;

import tp.pr5.mv.IO.InStrategy;
import tp.pr5.mv.IO.OutStrategy;
import tp.pr5.mv.cpu.ExecuteManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exception.ExceptionStack;
import tp.pr5.mv.ins.Instruction;

public class Jumpind implements Instruction {

	public Jumpind() {

	}

	public void execute(Memory<Integer> memoria, OperandStack<Integer> pila,
			ExecuteManager gestor, InStrategy in, OutStrategy out)
			throws ExceptionStack {
		if (pila.getCima() == 0)
			throw new ExceptionStack("Error en la instruccion "
					+ this.toString() + " No hay operandos suficientes");
		else {
			int argumento = pila.pop();
			if (argumento == gestor.getCurrentPC()) {
				gestor.setNextPC(argumento - 1);
				gestor.setCurrentPC(argumento - 1);
			} else
				gestor.setNextPC(argumento);
		}

	}

	public Instruction parseIns(String cadena) {
		String[] t = cadena.split(" ");
		boolean correcto = false;
		Instruction ins;

		if (t[0].equalsIgnoreCase("jumpind") && (t.length == 1))
			correcto = true;

		if (correcto == true)
			ins = new Jumpind();
		else
			ins = null;

		return ins;

	}

	public String toString() {
		return "JUMPIND ";
	}

}
