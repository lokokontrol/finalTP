package tp.pr5.mv.gui.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;

import tp.pr5.mv.Controlers.SwingControler;
import tp.pr5.mv.Observable.CPUObserver;
import tp.pr5.mv.Observable.MemoryObserver;
import tp.pr5.mv.Observable.Observable;
import tp.pr5.mv.cpu.Memory;
import tp.pr5.mv.cpu.OperandStack;
import tp.pr5.mv.cpu.ProgramMV;
import tp.pr5.mv.ins.Instruction;

public class MemoryPanel extends JPanel implements MemoryObserver<Integer>, CPUObserver{

	private static final long serialVersionUID = 1L;
	private SwingControler ctrl; 
	private MVTableModel model; 
	private JTextField memPos; 
	private JTextField memValue;


	private class MVTableModel extends AbstractTableModel {
		
		private static final long serialVersionUID = 1L;

		private TreeMap<Integer, Integer> content;
		String [] colNames = {"Address","Value"}; 
		
		public MVTableModel(){
			content = new TreeMap<Integer,Integer>();
		}
		
		public void setValue(int index, int value){
			content.put(index, value);
			fireTableDataChanged();	
		}
		
		public String getColumnName(int col){
			return colNames[col].toString();
		}
		
		@Override
		public int getColumnCount() {
			return colNames.length;
		}
			
		@Override
		public int getRowCount() {
			return content.size();
		}
	
	
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Collection <Integer> keys = content.keySet();
			int j=0;
			for (Integer i : keys) {
				if (j == rowIndex)
					if(columnIndex == 0)
						return i;
					else
						return content.get(i);
				else
					j++;
			}
			return null;	
		}
		
		public void reset(){
			content.clear();
			fireTableDataChanged();	
		}
		
	}
	
	
	
	public MemoryPanel(SwingControler ctrl,Observable<MemoryObserver<Integer>> memory,Observable<CPUObserver> cpu) { 
		this.ctrl = ctrl;
		cpu.addObserver(this);
		memory.addObserver(this);
		initGUI();
	}
	
	private void initGUI() { 
		//...
		//
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Memory"));
		model = new MVTableModel();
		JTable table = new JTable(model);
		JPanel extraPanel = new JPanel();
		JLabel etiquetaPosition = new JLabel("Position");
		memPos = new JTextField(5);
		JLabel etiquetaValue = new JLabel("Value");
		memValue = new JTextField(5);
		JButton setButton = new JButton("Set");
		extraPanel.add(etiquetaPosition);
		extraPanel.add(memPos);
		extraPanel.add(etiquetaValue);
		extraPanel.add(memValue);
		extraPanel.add(setButton);
		setButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				try {
					ctrl.write(Integer.parseInt(memPos.getText()),Integer.parseInt(memValue.getText()));
				}catch (NumberFormatException c){
					reportError("Valor invalido", "Excepcion");
				}
			}

			private void reportError(String msg, String title) { 
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame,msg,title,JOptionPane.WARNING_MESSAGE);
		}
		 });
	    this.add(new JScrollPane(table),BorderLayout.CENTER);
		this.add(extraPanel, BorderLayout.SOUTH);
		
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

	@Override
	public void onWrite(final int index,final Integer value) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				model.setValue(index, value);	
			}
		});
		
	}

	@Override
	public void onMemReset() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				model.reset();
			}
		});
		
	}


	
}
