package tp.pr5.mv.ins;

import tp.pr5.mv.aritmeticas.*;
import tp.pr5.mv.booleanas.*;
import tp.pr5.mv.comparacion.*;
import tp.pr5.mv.exception.ExceptionInsIncorrecta;
import tp.pr5.mv.otras.*;
import tp.pr5.mv.salto.*;

public class InstructionParser {

	private static Instruction[] instr = { new Add(), new Div(), new Mul(),
			new Sub(), new And(), new Not(), new Or(), new Eq(), new Gt(),
			new Le(), new Lt(), new Bf(0), new Bt(0), new Jump(0), new Rbf(0),
			new Rbt(0), new Rjump(0), new Dup(), new Flip(), new Halt(),
			new Load(0), new Neg(), new Out(), new In(), new Pop(),
			new Push(0), new Store(0), new Jumpind(), new LoadInd(),
			new StoreInd() };

	public static Instruction parseInstruction(String line)
			throws ExceptionInsIncorrecta {

		Instruction cc = null;

		for (Instruction c : instr)
			if (cc == null)
				cc = c.parseIns(line);

		if (cc == null)
			throw new ExceptionInsIncorrecta("Instruccion incorrecta");
		return cc;

	}

	/**
	 * 
	 * @param argumento
	 *            Es el argumento de la instruccion pasado como String.
	 * @return Devuelve si el argumento de la instruccion es un numero(true) o
	 *         no (false).
	 */
	public static boolean esNumerico(String argumento) {
		boolean numero = true;
		int i = 0;

		if (argumento.charAt(0) == '-')
			i = 1;

		while (i < argumento.length() && numero) {
			if (argumento.charAt(i) < '0' || argumento.charAt(i) > '9')
				numero = false;
			i++;
		}
		return numero;

	}

}
