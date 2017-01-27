package tp.pr5.mv.AAA.main;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class ArgumentosCli {

	private final String asm = "a";// asm carga de archivo
	private final String help = "h";// Ayuda
	private final String in = "i";// Fichero entrada
	private final String mode = "m";// Modo batch o interactivo
	private final String out = "o";// Fichero de salida

	private final String programa = "usage: tp.pr3.mv.Main [-a <asmfile>] [-h] [-i <infile>] [-m <mode>] [-o <outfile>]";

	private String asmname;
	private String infilename;
	private String outfilename;
	private String modename = "interactive";
	private Boolean h = false;

	CommandLine cmd;
	Options options;

	//
	ArgumentosCli(String[] args) throws ParseException {
		options = new Options();
		options.addOption(asm,"asm",true,"Fichero con el codigo en ASM del programa a ejecutar. Obligatorio en modo batch.");
		options.addOption(help, "help", false, "Muestra esta ayuda");
		options.addOption(in, "in", true,"Entrada del programa de la maquina-p.");
		options.addOption(mode, "mode", true,"Modo de funcionamiento (batch | interactive). Por defecto, batch.");
		options.addOption(out, "out", true,"Fichero donde se guarda la salida del programa de la maquina-p.");

		CommandLineParser parser = new GnuParser();
		cmd = parser.parse(options, args);

		extractValues();
		validate();
	}

	// Coge los valores de los argumentos que has pasado
	private void extractValues() {
		if (cmd.hasOption(asm)) {
			asmname = cmd.getOptionValue(asm);
		}
		if (cmd.hasOption(in)) {
			infilename = cmd.getOptionValue(in);
		}
		if (cmd.hasOption(mode)) {
			modename = cmd.getOptionValue(mode);
		}
		if (cmd.hasOption(out)) {
			outfilename = cmd.getOptionValue(out);
		}
		if (cmd.hasOption(help)) {
			h = true;
		}
	}

	// Comprueba que el modo es un moo correcta, y si Es modo batch, comprueba que haya ruta
	private void validate() throws ParseException {
		if ((asmname == null) && (modename.equalsIgnoreCase("batch") || (modename.equalsIgnoreCase("window")) && h == false))
			throw new ParseException("Necesitas ruta de fichero");
		if (!modename.equalsIgnoreCase("interactive") && !modename.equalsIgnoreCase("batch")&& !modename.equalsIgnoreCase("window"))
			throw new ParseException(
					"Modo incorrecto (parametro -m|--mode Use -h|--help para más detalles.");
	}

	//Metodo que muestra la ayuda formateada
	public void usage() {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp(programa, options);
	}

	public String getModename() {
		return modename;
	}

	public String getAsmname() {
		return asmname;
	}

	public String getInfilename() {
		return infilename;
	}

	public String getOutfilename() {
		return outfilename;
	}

	public Boolean getH() {
		return h;
	}

}
