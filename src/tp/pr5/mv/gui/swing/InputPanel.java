package tp.pr5.mv.gui.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;


import java.io.FileNotFoundException;






import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import tp.pr5.mv.Controlers.SwingControler;
import tp.pr5.mv.IO.InStrategy;



public class InputPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SwingControler ctrl;
	private JTextArea inputTextArea;
	
	public InputPanel(SwingControler ctrl) throws FileNotFoundException { 
		this.ctrl = ctrl;
		initGUI();
		
	}
	
	private void initGUI() throws FileNotFoundException {
		//...
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Input"));
		this.setMaximumSize(new Dimension(3,20));
		inputTextArea = new JTextArea(3,20);
		inputTextArea.setFont(new Font("Courier", Font.PLAIN, 16));
		inputTextArea.setEditable(false);
		this.add(new JScrollPane(inputTextArea));
		
		InStrategy inCurr = ctrl.getInStrategy(); 
		InStrategy inNew = new InStrategyGUI(inCurr, inputTextArea); 
		ctrl.setInStrategy( inNew );
	}

}
