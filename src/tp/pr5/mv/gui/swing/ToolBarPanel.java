package tp.pr5.mv.gui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import tp.pr5.mv.Controlers.SwingControler;
import tp.pr5.mv.Observable.CPUObserver;
import tp.pr5.mv.Observable.Observable;
import tp.pr5.mv.Views.SwingView;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.ins.Instruction;



public class ToolBarPanel extends JPanel implements CPUObserver{
	
	private static final long serialVersionUID = 1L;
	private SwingControler ctrl;
	JButton stepButton;
	JButton runButton;
	JButton quitButton;
	JButton pauseButton;
	JButton resetButton;
	JButton ficheroButton;
	
	public ToolBarPanel(SwingControler ctrl, Observable<CPUObserver> cpu) {
		this.ctrl = ctrl;
		cpu.addObserver(this);
		initGUI();
	 }
	
	 private void initGUI() {
		 stepButton = new JButton(); 
		 stepButton.setIcon(createImageIcon("step.png"));
		 stepButton.setText("Step");
		 stepButton.setToolTipText("Step");
		 this.add(stepButton);
		 stepButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
					ctrl.step();
			 }
		 }); 
		 runButton = new JButton(); 
		 runButton.setIcon(createImageIcon("run.png"));
		 runButton.setText("Run");
		 runButton.setToolTipText("Run");
		 this.add(runButton);
		 runButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 new Thread() {
						public void run() {
							ctrl.run();
						}	
					}.start();	
					
			 }
		 });
		 pauseButton = new JButton(); 
		 pauseButton.setIcon(createImageIcon("pause.png"));
		 pauseButton.setText("Pause");
		 pauseButton.setToolTipText("Pause");
		 this.add(pauseButton);
		 pauseButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 ctrl.pause();
			 }
		 });
		 resetButton = new JButton(); 
		 resetButton.setIcon(createImageIcon("reset.png"));
		 resetButton.setText("Reset");
		 resetButton.setToolTipText("Reset");
		 this.add(resetButton);
		 resetButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
					ctrl.reset();
			 }
		 }); 
		 ficheroButton = new JButton(); 
		 ficheroButton.setIcon(createImageIcon("fichero.png"));
		 ficheroButton.setText("Load");
		 ficheroButton.setToolTipText("Load");
		 this.add(ficheroButton);
		 ficheroButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JFileChooser file = new JFileChooser();
				 int returnVal =  file.showOpenDialog(null);
				 if (returnVal ==  JFileChooser.APPROVE_OPTION){
					 File fich = file.getSelectedFile();
					 String nombrePrograma = fich.getAbsolutePath();
					 ctrl.cargarFichero(nombrePrograma);
				 }
			 }
		 });
		 quitButton = new JButton(); 
		 quitButton.setIcon(createImageIcon("exit.png"));
		 quitButton.setText("Quit");
		 quitButton.setToolTipText("Quit");
		 this.add(quitButton);
		 quitButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 JFrame frame = new JFrame();
					int salimos = JOptionPane.showOptionDialog(frame, "� Esta seguro ?", "Salir", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
					if (salimos ==0)
						ctrl.quit();
		 }
		 });
		 }
	private static ImageIcon createImageIcon(String path) { 
		java.net.URL imgURL = SwingView.class.getResource(path);
		if (imgURL != null) 
			return new ImageIcon(imgURL);
		return null;
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
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				stepButton.setEnabled(false);
				runButton.setEnabled(false);
			}
		});
		
	}

	@Override
	public void onEndRun() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				stepButton.setEnabled(true);
				runButton.setEnabled(true);
			}
		});
			
	}

	@Override
	public void onError(String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHalt() {
		stepButton.setEnabled(true);
		runButton.setEnabled(true);
	}

	@Override
	public void onReset(ProgramMV<Instruction> program) {
		// TODO Auto-generated method stub
		
	}

}
