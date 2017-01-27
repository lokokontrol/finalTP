package tp.pr5.mv.Views;

import tp.pr5.mv.Observable.CPUObserver;
import tp.pr5.mv.Observable.Observable;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.ins.Instruction;

public class BatchView implements CPUObserver{

	
	public BatchView(Observable<CPUObserver> cpu){
		cpu.addObserver(this);
	}
	
	@Override
	public void onError(String msg) {
		System.err.println(msg);
	}
	
	@Override
	public void onStartInstrExecution(Instruction ins) {
		
	}

	@Override
	public void onEndInstrExecution(int pc, Memory<Integer> memoria, OperandStack<Integer> pila) {
		// TODO Auto-generated method stub
		
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
