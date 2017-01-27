package tp.pr5.mv.Views;

import tp.pr5.mv.AAA.main.Constantes;
import tp.pr5.mv.Observable.CPUObserver;
import tp.pr5.mv.Observable.Observable;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.ins.Instruction;

public class InteractiveView implements CPUObserver{

	public InteractiveView(Observable<CPUObserver> cpu){
		cpu.addObserver(this);
	}
	
	@Override
	public void onError(String msg) {
		System.err.println(msg);
	}
	
	@Override
	public void onStartInstrExecution(Instruction ins) {
			System.out.println("Comienza la ejecucion de " + ins.toString());		
	}

	@Override
	public void onEndInstrExecution(int pc, Memory<Integer> memoria, OperandStack<Integer> pila) {
		System.out.println( "El estado de la maquina tras ejecutar la instruccion es: "
				+ Constantes.Salto + "Memoria: " + memoria.toString()
				+ Constantes.Salto + "Pila de operandos: " + pila.toString());
	}

	@Override
	public void onStartRun() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEndRun() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onHalt() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onReset(ProgramMV<Instruction> program) {
		// TODO Auto-generated method stub
		
	}



}
