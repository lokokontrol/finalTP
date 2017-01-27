package tp.pr5.mv.gui.swing;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JTextArea;

import tp.pr5.mv.Controlers.SwingControler;
import tp.pr5.mv.IO.InStrategy;

public class InStrategyGUI implements InStrategy{
	
	JTextArea inputTextArea;
	InStrategy old;
	StringBuilder content = new StringBuilder();
	StringBuilder contentOriginal = new StringBuilder();
	int pos;
	String fname;
	FileReader lectorArchivo;
	SwingControler ctrl;
	
	public InStrategyGUI(InStrategy old, JTextArea inputTextArea) throws FileNotFoundException {
		
		// 1. leer toda la entrada del old, y construir el StringBuilder content
		// 2. mostrar el contenido de content en el inputTextArea
		
		this.inputTextArea = inputTextArea;
		this.old = old;
		this.pos=0;
		try {
			int aux = old.read();
			while (aux != -1){
				content.append((char)aux);
				aux = old.read();
			}
			contentOriginal.append(content);
			inputTextArea.setText(content.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	 

	@Override
	public void open() throws FileNotFoundException {
		// TODO Auto-generated method stub
	}

	@Override
	public void close() throws IOException {
		old.close();
	}

	@Override
	public int read() throws IOException {
		if (!inputTextArea.getText().isEmpty() && pos < content.length()){
			int aux = content.charAt(pos);
			if ((aux !=10) && (aux != 13) && (aux !=32) ){
				content.setCharAt(pos, '*');
				inputTextArea.setText(content.toString());
				pos=pos+1;
				return aux; 
			}
			else{
				pos=pos+1;
				return aux; // Aqui dudo si es -1 , no se como se pide.
			}
		}
		else{
			return -1;
		}
	}
	
	
	public void reset(){
		this.pos=0;
		content.delete(0, content.length());
		content.append(contentOriginal);
		inputTextArea.setText(content.toString());
	}
			
	
	}


