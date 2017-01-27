package tp.pr5.mv.cpu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tp.pr5.mv.AAA.main.ArgumentosCli;
import tp.pr5.mv.AAA.main.Constantes;
import tp.pr5.mv.exception.ExceptionDivisionByZero;
import tp.pr5.mv.exception.ExceptionInsIncorrecta;
import tp.pr5.mv.exception.ExceptionStack;
import tp.pr5.mv.ins.Instruction;
import tp.pr5.mv.ins.InstructionParser;

public class ProgramMV<T> {

	ArrayList<T> program;

	public ProgramMV() {
		program = new ArrayList<T>();
	}

	public void addInstruction(T ins) {
		program.add(ins);
	}

	public T getInstruction(int ins) {
		return program.get(ins);

	}

	public int getContador() {
		return program.size();
	}

	@Override
	public String toString() {
		String s = "";
		int i = 0;
		for (T aux : program) {
			if (aux != null)
				s = s + i + ": " + aux.toString() + Constantes.Salto;
			i++;
		}
		return s;
	}
	
	public static ProgramMV<Instruction> CargarParsearDeFichero(String ruta_fichero) throws ExceptionInsIncorrecta {
		ProgramMV<Instruction> programa = new ProgramMV<Instruction>();
		String leido = "";
		int aux = 0;
		if (ruta_fichero != null) {
			BufferedReader reader;
			try {
				reader = new BufferedReader(new FileReader(ruta_fichero));
				while (reader.ready()) {// mientras no se llegue al final del
										// fichero
					leido = reader.readLine().trim();// guardamos la linea leida
					if (leido.length() != 0 && leido.charAt(0) != ';') {
						if (leido.indexOf(';') > 0) {
							aux = leido.indexOf(';');
							leido = leido.substring(0, aux);
							if (InstructionParser.parseInstruction(leido) != null)
								programa.addInstruction(InstructionParser
										.parseInstruction(leido));
						} else {
							if (InstructionParser.parseInstruction(leido) != null)
								programa.addInstruction(InstructionParser
										.parseInstruction(leido));
						}

					}
				}
			} catch (FileNotFoundException e) {
				System.err.println(e + "\nFichero no encontrado."
						+ " Indique una ruta valida."); // si la ruta no es
														// correcta
			} catch (IOException e) {
				System.err.println(e + "\nError de hardware. "
						+ " Consulte la documentacion.");// otro tipo de
															// problemas
			} catch (ExceptionInsIncorrecta e) {
				System.err.println("Error en el programa. Linea: " + leido
						+ " "+e.getMessage());// otro tipo de problemas
				System.exit(1);
			}
		}
		
		
		return programa;

	}
	
	public static ProgramMV<Instruction> DimeProgramaPorFichero (CPU cpu, String ruta,ArgumentosCli tipoModo) throws ExceptionStack, ExceptionDivisionByZero, ExceptionInsIncorrecta{
		ProgramMV<Instruction> pro;
		ruta = tipoModo.getAsmname();
		//Parsea el fichero
		pro = CargarParsearDeFichero(ruta);
		return pro;
}
	
	public static ProgramMV<Instruction> DimeProgramaPorConsola (CPU cpu, Scanner sc) throws ExceptionStack, ExceptionDivisionByZero, ExceptionInsIncorrecta{
		ProgramMV<Instruction> pro = new ProgramMV<Instruction>();
		String instruccion=" ";
		System.out.println("Introduce el programa fuente:");
		instruccion = sc.nextLine();
		while (!instruccion.equalsIgnoreCase("end")){   // Vamos introduciendo las instrucciones
			try {
			Instruction ins = InstructionParser.parseInstruction(instruccion); // Parseamos la instruccion
			pro.addInstruction(ins); // Añadimos a nuestro programa 
			} catch (ExceptionInsIncorrecta e){
				System.out.println("Error: " + e.getMessage());
			}
			instruccion = sc.nextLine();
		}
		return pro;
	}

}
