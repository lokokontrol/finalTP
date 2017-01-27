package tp.pr5.mv.gui.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import tp.pr5.mv.Observable.CPUObserver;
import tp.pr5.mv.Observable.MemoryObserver;
import tp.pr5.mv.Observable.Observable;
import tp.pr5.mv.Observable.StackObserver;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.ins.Instruction;

public class StatusBarPanel extends JPanel implements StackObserver<Integer>, MemoryObserver<Integer>, CPUObserver {
	
	private JCheckBox pilaCheck;
	private JCheckBox memoryCheck;
	private JLabel instruccionEjecutada;
	private JLabel maquinaestado;
	private JLabel numinstrucciones;
	private int contador=0;
	private String estado;
	private Color color;
	private String estadomaquina = "Virtual Machine";


	private static final long serialVersionUID = 1L;
	
	public StatusBarPanel(Observable<StackObserver<Integer>> stack, Observable<MemoryObserver<Integer>> memory, Observable<CPUObserver> cpu) {
		cpu.addObserver(this);
		stack.addObserver(this);
		memory.addObserver(this);
		initGUI();
	}

	private void initGUI() { 
	
		pilaCheck = new JCheckBox("Pila Modificada");
		memoryCheck = new JCheckBox("Memoria Modificada");
		maquinaestado = new JLabel(estadomaquina);
		instruccionEjecutada = new JLabel(estado);
		numinstrucciones = new JLabel();
		maquinaestado.setFont(new Font("Courier", Font.PLAIN,12));
		instruccionEjecutada.setFont(new Font("Courier", Font.PLAIN,12));
		numinstrucciones.setFont(new Font("Courier", Font.PLAIN,12));
		
		this.add(maquinaestado,BorderLayout.WEST);
		this.add(instruccionEjecutada,BorderLayout.CENTER);
		this.add(numinstrucciones,BorderLayout.EAST);
		this.add(pilaCheck,BorderLayout.WEST);
		this.add(memoryCheck,BorderLayout.EAST);

	}
	
	void actualizarestado() {
	instruccionEjecutada.setText(estado);
	maquinaestado.setText(estadomaquina);
	maquinaestado.setForeground(color);
	numinstrucciones.setText("| Numero de instrucciones ejecutadas : "+contador);
	}

	
	@Override
	public void onStartInstrExecution(final Instruction ins) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				estado="Ejecutando: "+ins.toString();
				contador++;
				 if (!estadomaquina.equalsIgnoreCase("Maquina en ejecucion")) {
					 color=Color.black;
					 estadomaquina="Virtual Machine";
				 }

				actualizarestado();
				
			}
		});
		
	}
	
	@Override
	public void onEndInstrExecution(int pc, Memory<Integer> memoria,
			OperandStack<Integer> pila) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onStartRun() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				estadomaquina="Maquina en ejecucion";
				color=Color.green;
				actualizarestado();
			}
		});
			
	}
	
	@Override
	public void onEndRun() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				estadomaquina="Maquina finalizada";
				color=Color.red;
				actualizarestado();
			}
		});
		
	}
	
	@Override
	public void onError(String msg) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onHalt() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				estadomaquina="Maquina parada";
				color=Color.orange;
				actualizarestado();
			}
		});
	}
	
	@Override
	public void onReset(ProgramMV<Instruction> program) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				estadomaquina="Maquina reseteada";
				estado="";
				contador=0;
				color=Color.blue;
				actualizarestado();
			}
		});
		
	}
	
	@Override
	public void onWrite(int index, Integer value) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				memoryCheck.setSelected(true);
			}
		});
		
	}
	
	
	@Override
	public void onPush(Integer value) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				pilaCheck.setSelected(true);
			}
		});
	
	}
	
	@Override
	public void onPop(Integer value) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				pilaCheck.setSelected(true);
			}
		});
		
		
	}
	
	@Override
	public void onMemReset() {
		memoryCheck.setSelected(false);
	}
	
	@Override
	public void onStackReset() {
		pilaCheck.setSelected(false);
	}



}
