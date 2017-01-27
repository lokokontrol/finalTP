package tp.pr5.mv.gui.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import tp.pr5.mv.Controlers.SwingControler;
import tp.pr5.mv.IO.OutStrategy;

public class OutputPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SwingControler ctrl;
	private JTextArea outputTextArea;
	
	public OutputPanel(SwingControler ctrl) { 
		this.ctrl = ctrl;
		initGUI();
	}
	
	private void initGUI() {
		//..
		this.setLayout(new BorderLayout());

		this.setBorder(new TitledBorder("Output"));
		this.setMaximumSize(new Dimension(3,20));
		outputTextArea = new JTextArea(3,20);
		outputTextArea.setFont(new Font("Courier", Font.PLAIN, 16));
		outputTextArea.setEditable(false);
		this.add(new JScrollPane(outputTextArea));
		
		OutStrategy outCurr = ctrl.getOutStrategy() ;
		OutStrategy outNew = new OutStrategyGUI( outCurr, outputTextArea);
		ctrl.setOutStrategy( outNew );
	}

}
