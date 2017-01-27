package tp.pr5.mv.gui.swing;



import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

import tp.pr5.mv.Controlers.SwingControler;
import tp.pr5.mv.Observable.CPUObserver;
import tp.pr5.mv.Observable.Observable;
import tp.pr5.mv.Observable.StackObserver;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.ins.Instruction;



public class StackPanel extends JPanel implements StackObserver<Integer>,CPUObserver{

	
	private static final long serialVersionUID = 1L;
	private SwingControler ctrl;
	private JList<Integer> stackArea;
	private JTextField stackElem;
	private DefaultListModel<Integer> model;
	private JButton pushButton;
	private JButton popButton;
	
	public StackPanel(SwingControler ctrl,Observable<StackObserver<Integer>> stack,Observable<CPUObserver> cpu) {
		this.ctrl = ctrl;
		cpu.addObserver(this);
		stack.addObserver(this);
		initGUI();
	}
	
	private void initGUI() {
		//...
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Operand Stack"));
		model = new DefaultListModel<Integer>();
		stackArea = new JList<Integer>(model);
		JPanel extraPanel = new JPanel();
		JLabel etiqueta = new JLabel("Value");
		stackElem = new JTextField(9);
		extraPanel.add(etiqueta);
		extraPanel.add(stackElem);
		pushButton = new JButton("Push");
		popButton = new JButton("Pop");
		extraPanel.add(pushButton);
		pushButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 try{
					ctrl.push(Integer.parseInt(stackElem.getText()));
				 }catch (NumberFormatException c){
					reportError("Valor invalido. Introduce un numero", "Excepcion");
				}
			 }
				private void reportError(String msg, String title) { 
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame,msg,title,JOptionPane.WARNING_MESSAGE);
				}
		 });
		 extraPanel.add(popButton);
		 popButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
					ctrl.pop();
			 }
		 });
		
		this.add(stackArea,BorderLayout.CENTER);
		this.add(extraPanel,BorderLayout.SOUTH);
		this.add(new JScrollPane(stackArea),BorderLayout.CENTER);
		// ...
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
				pushButton.setEnabled(false);
				popButton.setEnabled(false);
			}
		});
		
	}

	@Override
	public void onEndRun() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				pushButton.setEnabled(true);
				popButton.setEnabled(true);
			}
		});
	
	}

	@Override
	public void onError(String msg) {
		
	}

	@Override
	public void onHalt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReset(ProgramMV<Instruction> program) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onPush(final Integer value) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				model.addElement(value);
				stackArea.setModel(model);
			}
		});
	
	}

	@Override
	public void onPop(Integer value) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				model.removeElementAt(model.size()-1);
				stackArea.setModel(model);
			}
		});
		
	}

	@Override
	public void onStackReset() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				model.clear();
				stackArea.setModel(model);
			}
		});
		
	}

}
