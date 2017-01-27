package tp.pr5.mv.AAA.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


import tp.pr5.mv.Controlers.BatchControler;
import tp.pr5.mv.Controlers.InteractiveControler;
import tp.pr5.mv.Controlers.SwingControler;
import tp.pr5.mv.IO.InStrategy;
import tp.pr5.mv.IO.InStrategyFile;
import tp.pr5.mv.IO.InStrategyNada;
import tp.pr5.mv.IO.OutStrategy;
import tp.pr5.mv.IO.OutStrategyFile;
import tp.pr5.mv.IO.OutStrategyStd;
import tp.pr5.mv.Views.BatchView;
import tp.pr5.mv.Views.InteractiveView;
import tp.pr5.mv.Views.SwingView;
import tp.pr5.mv.cpu.CPU;
import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.exception.ExceptionDivisionByZero;
import tp.pr5.mv.exception.ExceptionInsIncorrecta;
import tp.pr5.mv.exception.ExceptionMemoryNull;
import tp.pr5.mv.exception.ExceptionStack;
import tp.pr5.mv.exception.MVError;
import tp.pr5.mv.ins.Instruction;


public class TPMV {

	public static boolean esBatch = true;
	private static final int _BATCH_MODE = 0;
	private static final int _INTER_MODE = 1;
	private static final int _WINDOW_MODE = 2;
	private static final int _NULL_STREAM = 3;
	private static final int _STD_STREAM = 4;
	private static final int _FILE_STREAM = 5;
	private static CPU cpu;
	private static int executionMode = _INTER_MODE;
	private static int inStreamMode = _NULL_STREAM;
	private static int outStreamMode = _NULL_STREAM;
	private static String inStreamFileName;
	private static String outStreamFileName;
	private static String programFileName;
	private static ArgumentosCli cli;

	public static void main(String[] args)  {
		startMV(args);
	}
	
	private static void startMV(String[] args)  {
		try {
			cli = new ArgumentosCli(args);
			cpu = new CPU();
			parseOption(args);
			dimeOpcion(cli, inStreamFileName, outStreamFileName, cpu);
			mostrarAyuda();
			switch (executionMode) {
			case _INTER_MODE:
				interactiveMode();
				break;
			case _BATCH_MODE:
				batchMode();
				break;
			case _WINDOW_MODE:
				windowMode();
				break;
			}
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			System.exit(1);
		}

	}
	
private static void parseOption(String[] args) {
		
		programFileName = cli.getAsmname();
	
		if (cli.getInfilename() != null) {
			inStreamMode = _FILE_STREAM;  
			inStreamFileName = cli.getInfilename();
		}
		if (cli.getOutfilename() != null) {
			outStreamMode = _FILE_STREAM;  
			outStreamFileName = cli.getOutfilename();
		}
		
		if (cli.getModename().equalsIgnoreCase("batch")){
			executionMode = _BATCH_MODE;
			if (cli.getOutfilename() == null) outStreamMode =  _STD_STREAM;
			if (cli.getInfilename() == null) inStreamMode =  _STD_STREAM;
		}
		else if (cli.getModename().equalsIgnoreCase("interactive")){
			executionMode = _INTER_MODE;
		}
		else if (cli.getModename().equalsIgnoreCase("window")){
			executionMode = _WINDOW_MODE;
			if (cli.getOutfilename() == null) outStreamMode =  _STD_STREAM;
			if (cli.getInfilename() == null) inStreamMode =  _STD_STREAM;
		}
		
		if (cli.getInfilename() != null)
			inStreamMode = _FILE_STREAM;  
		else
			inStreamMode = _NULL_STREAM;
		
		if (cli.getOutfilename() != null)
			outStreamMode = _FILE_STREAM;
		else
			outStreamMode = _NULL_STREAM;
	}
	
public static void dimeOpcion(ArgumentosCli tipoModo, String rutaLeer, String rutaEscribir, CPU cpu) throws MVError {
		
		InStrategy in = null;
		OutStrategy out = null;
		if (executionMode == _BATCH_MODE || executionMode == _INTER_MODE || executionMode == _WINDOW_MODE){
			if ((outStreamMode == _FILE_STREAM) && (inStreamMode == _FILE_STREAM)) {
				in = new InStrategyFile(rutaLeer);
				out = new OutStrategyFile(rutaEscribir);
			}
			if ((outStreamMode == _NULL_STREAM) && (inStreamMode == _NULL_STREAM)) {
				in = new InStrategyNada();
				out = new OutStrategyStd();
			}
			if ((outStreamMode == _FILE_STREAM) && (inStreamMode == _NULL_STREAM)) {
				in = new InStrategyNada();
				out = new OutStrategyFile(rutaEscribir);
			}
			if ((outStreamMode == _NULL_STREAM) && (inStreamMode == _FILE_STREAM)) {
				in = new InStrategyFile(rutaLeer);
				out = new OutStrategyStd();
			}
		}
		 cpu.setInStrategy(in);
		 cpu.setOutStrategy(out);
	}

	private static void mostrarAyuda(){
		if ( cli.getH() ) {
			cli.usage();
		}
	}

	

	private static void windowMode() throws ExceptionStack, ExceptionDivisionByZero, ExceptionInsIncorrecta, IOException {
	    cpu.getInStrategy().open();
	    cpu.getOutStrategy().open();
		String ruta="";
		ProgramMV<Instruction> pro;
		pro = ProgramMV.DimeProgramaPorFichero(cpu, ruta, cli);
		cpu.loadProgram(pro);
		cpu.setDelay(300);
		final SwingControler ctrl = new SwingControler(cpu);
		JFrame.setDefaultLookAndFeelDecorated(true);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						try {
							@SuppressWarnings("unused")
							SwingView view = new SwingView(ctrl,cpu,cpu.getPila(),cpu.getMemory());
						} catch (FileNotFoundException e) {}
							
					}
				});
			}
		});
		ctrl.start();
	}

	private static void batchMode() throws ExceptionInsIncorrecta, IOException, ExceptionStack, ExceptionDivisionByZero {
		cpu.getInStrategy().open();
		cpu.getOutStrategy().open();
		
		String ruta="";
		ProgramMV<Instruction> pro;
		pro = ProgramMV.DimeProgramaPorFichero(cpu, ruta, cli);
		cpu.loadProgram(pro);
		cpu.setDelay(0);
		BatchControler ctrl = new BatchControler(cpu);
		@SuppressWarnings("unused")
		BatchView view = new BatchView(cpu);
		ctrl.start();
		
		cpu.getInStrategy().close();
		cpu.getOutStrategy().close();
		
	}
	
	
	private static void interactiveMode() throws IOException, ExceptionMemoryNull, ExceptionStack, ExceptionDivisionByZero, ExceptionInsIncorrecta, MVError {
		String ruta="";
		cpu.getInStrategy().open();
		cpu.getOutStrategy().open();
		ProgramMV<Instruction> pro;
		Scanner sc = new Scanner(System.in); 
		if (programFileName != null){// Obtenemos programa de Fichero
			pro = ProgramMV.DimeProgramaPorFichero(cpu, ruta, cli);
		}
		else // Obtenemos programa de Consola
			pro = ProgramMV.DimeProgramaPorConsola(cpu, sc);
		
		System.out.println(pro); // Mostramos el programa
		cpu.loadProgram(pro);
		cpu.setDelay(0);
		InteractiveControler ctrl = new InteractiveControler(cpu);
		@SuppressWarnings("unused")
		InteractiveView view = new InteractiveView(cpu);
		ctrl.start();
		
		cpu.getInStrategy().close();
		cpu.getOutStrategy().close();
	}
	
	
		
		
	}

