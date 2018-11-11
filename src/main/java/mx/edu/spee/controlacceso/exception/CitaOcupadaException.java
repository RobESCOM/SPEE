package mx.edu.spee.controlacceso.exception;

public class CitaOcupadaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 31862154209686624L;

	public CitaOcupadaException() {
		super();
	}
	
	public CitaOcupadaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CitaOcupadaException(String message, Throwable cause) {
		super(message, cause);
	}

	public CitaOcupadaException(String message) {
		super(message);
	}

	public CitaOcupadaException(Throwable cause) {
		super(cause);
	}
}
