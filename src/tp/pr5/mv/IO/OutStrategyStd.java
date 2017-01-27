package tp.pr5.mv.IO;

public class OutStrategyStd implements OutStrategy {

	public void open() {
	}

	public void close() {
	}

	public void write(int c) {
		System.out.print((char) c);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
