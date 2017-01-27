package tp.pr5.mv.IO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InStrategyFile implements InStrategy {
	String fname;
	FileReader f;

	public InStrategyFile(String fname) {
		this.fname = fname;
	}

	public void open() throws FileNotFoundException {
		f = new FileReader(fname);
	}

	public void close() throws IOException {
		f.close();
	}

	public int read() throws IOException {
		if (f.ready()) {
			return f.read();
			
		} else
			return -1;
	}

	@Override
	public void reset() {
		
		
	}
}
