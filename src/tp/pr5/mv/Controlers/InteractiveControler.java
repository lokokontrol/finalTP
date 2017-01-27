package tp.pr5.mv.Controlers;


import java.util.Scanner;

import tp.pr5.mv.AAA.main.TPMV;

import tp.pr5.mv.cpu.CPU;

import tp.pr5.mv.ins.InstructionParser;

public class InteractiveControler extends Controler{

	public InteractiveControler(CPU cpu) {
		super(cpu);
	}

	public void start() {
		
		TPMV.esBatch = false;
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print(">");
		boolean done = false;
		do {
			String cmd = sc.nextLine();
			String[] tokens = cmd.split(" ");
			if (tokens[0].equalsIgnoreCase("push") && (tokens.length == 2) && InstructionParser.esNumerico(tokens[1]))
				this.push(Integer.parseInt(tokens[1]));
			if (tokens[0].equalsIgnoreCase("pop") && (tokens.length == 1))
				this.pop();
			if (tokens[0].equalsIgnoreCase("write") && (tokens.length == 3) && InstructionParser.esNumerico(tokens[1]) && InstructionParser.esNumerico(tokens[2]))
				this.write(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
			if (tokens.length == 1 && tokens[0].equalsIgnoreCase("step"))
				this.step();
			if (tokens.length == 1 && tokens[0].equalsIgnoreCase("run")) 
				this.run();
			if (tokens.length == 2 && tokens[0].equalsIgnoreCase("step") && (Integer.parseInt(tokens[1]) > 0))
				this.steps(Integer.parseInt(tokens[1]));
			if (tokens.length == 1 && tokens[0].equalsIgnoreCase("quit")) {
				done = true;
			}
		} while (!done );
		this.quit();
	}
	


}
