package tp.pr5.mv.gui.swing;

import java.io.IOException;

import javax.swing.JTextArea;

import tp.pr5.mv.IO.OutStrategy;

public class OutStrategyGUI implements OutStrategy{
	
	JTextArea outputTextArea;
	OutStrategy old;
	StringBuilder content = new StringBuilder();
	int pos;

	public OutStrategyGUI(OutStrategy old, JTextArea outputTextArea) {
		 this.old= old;
		 this.outputTextArea = outputTextArea;
	 }
	@Override
	public void open() throws IOException {
		
		
	}

	@Override
	public void close() throws IOException {
		old.close();
		
	}

	@Override
	public void write(int x) throws IOException {
		// Pasamos int x a un char
		char aux =(char) x; 
		old.write(aux);
		this.content.append(aux);
		outputTextArea.setText(content.toString());
		
	}
	@Override
	public void reset() {
		content.delete(0, content.length());
		outputTextArea.setText(content.toString());
	}

}
