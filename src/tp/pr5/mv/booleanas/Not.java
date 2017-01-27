package tp.pr5.mv.booleanas;

import tp.pr5.mv.IO.InStrategy;
import tp.pr5.mv.IO.OutStrategy;
import tp.pr5.mv.cpu.ExecuteManager;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.exception.ExceptionStack;
import tp.pr5.mv.ins.Instruction;

public class Not extends Booleanas {

	public Not() {

	}

	@Override
	public void execute(Memory<Integer> memoria, OperandStack<Integer> pila,
			ExecuteManager gestor, InStrategy in, OutStrategy out)
			throws ExceptionStack {
		if (pila.getCima() < 1)
			throw new ExceptionStack("No hay operandos suficientes");
		else {
			int n1 = pila.pop();
			if (operacion(n1))
				pila.push(0);
			else
				pila.push(1);
		}
		// return correcto;
	}

	public boolean operacion(int a) {
		if (a != 0)
			return true;
		else
			return false;
	}

	public Instruction parseIns(String cadena) {
		String[] t = cadena.split(" ");
		boolean correcto = false;
		Instruction ins;

		if (t[0].equalsIgnoreCase("not") && (t.length == 1))
			correcto = true;

		if (correcto == true)
			ins = new Not();
		else
			ins = null;

		return ins;
	}

	@Override
	protected boolean operacion(int a, int b) {
		// TODO Auto-generated method stub
		return false;
	}

}
