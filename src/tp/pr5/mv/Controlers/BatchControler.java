package tp.pr5.mv.Controlers;

import tp.pr5.mv.cpu.CPU;

public class BatchControler extends Controler {

	public BatchControler(CPU cpu) {
		super(cpu); // LLama al constructor de controler
	}

	@Override
	public void start() {
		super.run();
			
	}

}
