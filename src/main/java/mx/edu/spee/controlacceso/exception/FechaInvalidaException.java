package mx.edu.spee.controlacceso.exception;

public class FechaInvalidaException extends Exception {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6214304384423004907L;

	public FechaInvalidaException() {
		super();
	}
	
	public FechaInvalidaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FechaInvalidaException(String message, Throwable cause) {
		super(message, cause);
	}

	public FechaInvalidaException(String message) {
		super(message);
	}

	public FechaInvalidaException(Throwable cause) {
		super(cause);
	}

}
