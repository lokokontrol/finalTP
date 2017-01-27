package tp.pr5.mv.exception;

public class MVError extends Exception {

	private static final long serialVersionUID = 1L;

	public MVError(String mensaje) {
		super(mensaje);
	}

}
