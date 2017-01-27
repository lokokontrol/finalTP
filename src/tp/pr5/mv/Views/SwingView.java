package tp.pr5.mv.Views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.pr5.mv.Controlers.SwingControler;
import tp.pr5.mv.Observable.CPUObserver;
import tp.pr5.mv.Observable.MemoryObserver;
import tp.pr5.mv.Observable.Observable;
import tp.pr5.mv.Observable.StackObserver;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.gui.swing.InputPanel;
import tp.pr5.mv.gui.swing.MemoryPanel;
import tp.pr5.mv.gui.swing.OutputPanel;
import tp.pr5.mv.gui.swing.ProgramPanel;
import tp.pr5.mv.gui.swing.StackPanel;
import tp.pr5.mv.gui.swing.StatusBarPanel;
import tp.pr5.mv.gui.swing.ToolBarPanel;
import tp.pr5.mv.ins.Instruction;

public class SwingView extends JFrame implements CPUObserver{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SwingControler ctrl;
	private Observable<CPUObserver> cpu;
	private Observable<StackObserver<Integer>> stack;
	private Observable<MemoryObserver<Integer>> memory;
	private ToolBarPanel toolBar;
	private ProgramPanel program;
	private StackPanel stackPanel;
	private MemoryPanel memoryPanel;
	private InputPanel inpanel;
	private OutputPanel outpanel;
	private StatusBarPanel statusPanel;
	
	public SwingView (SwingControler ctrl,Observable<CPUObserver> cpu,Observable<StackObserver<Integer>> stack, Observable<MemoryObserver<Integer>> memory) throws FileNotFoundException{
		super("Virtual Machine");
		this.cpu=cpu;
		this.stack = stack;
		this.memory = memory;
		this.ctrl = ctrl;
		initGUI();
		cpu.addObserver(this);
	}
	
	private void initGUI() throws FileNotFoundException {
		
		toolBar = new ToolBarPanel(ctrl,cpu);
		program = new ProgramPanel(ctrl,cpu);
		stackPanel =  new StackPanel(ctrl,stack,cpu);
		memoryPanel = new MemoryPanel(ctrl,memory,cpu);
		outpanel = new OutputPanel(ctrl);
		inpanel = new InputPanel(ctrl);
		statusPanel =  new StatusBarPanel(stack, memory, cpu);
	//	ioPanel = new JPanel(new BorderLayout());
	//	centerPanel = new JPanel(new BorderLayout());
		
		// CON BORDERLAYOUT
		
		/*JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		ioPanel.add(inpanel,BorderLayout.CENTER);
		ioPanel.add(outpanel,BorderLayout.SOUTH);
		
		centerPanel.add(stack,BorderLayout.WEST);
		centerPanel.add(memory,BorderLayout.CENTER);
		centerPanel.add(ioPanel, BorderLayout.SOUTH);
		
		mainPanel.add( toolBar, BorderLayout.PAGE_START );
		mainPanel.add(program, BorderLayout.WEST);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		*/
		
		// CON GRIDBAGLAYOUT
		 
		JPanel mainPanel = new JPanel (new GridBagLayout());
		GridBagConstraints c= new GridBagConstraints();
		c.fill= GridBagConstraints.BOTH;
	
		c.gridx=0;c.gridy=0;
		c.gridwidth=3;c.gridheight=1;
		c.weightx=0;c.weighty=0;
		mainPanel.add(toolBar,c);
		
		c.gridx=0;c.gridy=1;
		c.gridwidth=1;c.gridheight=3;
		c.weightx=0;c.weighty=0;
		mainPanel.add(program,c);
		
		c.gridx=1;c.gridy=1;
		c.gridwidth=1;c.gridheight=1;
		c.weightx=0.5;c.weighty=0.5;
		mainPanel.add(stackPanel,c);
		
		c.gridx=2;c.gridy=1;
		c.gridwidth=1;c.gridheight=1;
		c.weightx=0.5;c.weighty=0.5;
		mainPanel.add(memoryPanel,c);
		
		c.gridx=1;c.gridy=2;
		c.gridwidth=2;c.gridheight=1;
		c.weightx=0.1;c.weighty=0.1;
		mainPanel.add(inpanel,c);
		
		c.gridx=1;c.gridy=3;
		c.gridwidth=2;c.gridheight=1;
		c.weightx=0.1;c.weighty=0.1;
		mainPanel.add(outpanel,c);
		
		c.gridx=0;c.gridy=4;
		c.gridwidth=3;c.gridheight=1;
		c.weightx=0.1;c.weighty=0;
		mainPanel.add(statusPanel,c);
		
		this.setContentPane(mainPanel);
		
		
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
			public void windowClosed(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}

			public void windowClosing(WindowEvent e) {
				ctrl.quit();
			}
			
		});
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null); // Para que por defecto aparezca en medio
		this.setVisible(true);
	}


	@Override
	public void onStartInstrExecution(Instruction ins) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEndInstrExecution(int pc, Memory<Integer> memoria,
			OperandStack<Integer> pila) {
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
	public void onError(String msg) {
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame,msg,"Excepcion",JOptionPane.WARNING_MESSAGE);
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
