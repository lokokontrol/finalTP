package tp.pr5.mv.IO;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface InStrategy {

	public void open() throws FileNotFoundException;

	public void close() throws IOException;

	public int read() throws IOException;
	
	public void reset();

}
