package tp.pr5.mv.gui.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import tp.pr5.mv.AAA.main.Constantes;
import tp.pr5.mv.Controlers.SwingControler;
import tp.pr5.mv.Observable.CPUObserver;
import tp.pr5.mv.Observable.Observable;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.ins.Instruction;

public class ProgramPanel extends JPanel implements CPUObserver{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SwingControler ctrl;
	private JTextArea programTextArea;
	private ProgramMV<Instruction> programa;
	int pc;

	public ProgramPanel(SwingControler ctrl, Observable<CPUObserver> cpu) {
		this.ctrl = ctrl;
		cpu.addObserver(this);
		initGUI();
	}
	
	private void initGUI() {
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Program"));
		programTextArea = new JTextArea(20, 15);
		programTextArea.setFont(new Font("Courier", Font.PLAIN, 16));
		programTextArea.setEditable(false);
		this.setMinimumSize(new Dimension(200,300));
		this.add(new JScrollPane(programTextArea));
		programa = ctrl.getProgram();
		showProgram();
	}
	
	public void showProgram() {
		String prog="";
		for (int i=0; i<programa.getContador();i++){
			if (this.pc == i)
				prog = prog + "*" + i + ":"+ programa.getInstruction(i).toString() + Constantes.Salto;
			else
				prog = prog + i + ":"+ programa.getInstruction(i).toString() + Constantes.Salto;
		}
		programTextArea.setText(prog);
	 }

	@Override
	public void onStartInstrExecution(Instruction ins) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEndInstrExecution(int pc, Memory<Integer> memoria, OperandStack<Integer> pila) {
		this.pc=pc;
		showProgram();
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
	public void onError(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHalt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReset(ProgramMV<Instruction> program) {
		this.pc = 0;
		programa = program;
		showProgram();
	}

	
}


