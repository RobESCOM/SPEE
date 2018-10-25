package mx.ipn.escom.spee.impresiones.exception;

public class ImpresionesInsuficientesException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ImpresionesInsuficientesException () {
		super();
	}
	
	public ImpresionesInsuficientesException (String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	public ImpresionesInsuficientesException (String message, Throwable cause) {
		super(message,cause);
	}
	
	public ImpresionesInsuficientesException (String message) {
		super(message);
	}
	
	public ImpresionesInsuficientesException (Throwable cause) {
		super(cause);
	}
}
