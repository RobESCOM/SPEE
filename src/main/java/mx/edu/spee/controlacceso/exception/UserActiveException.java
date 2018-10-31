package mx.edu.spee.controlacceso.exception;

public class UserActiveException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5360838153529803176L;

	public UserActiveException() {
		super();
	}
	
	public UserActiveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserActiveException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserActiveException(String message) {
		super(message);
	}

	public UserActiveException(Throwable cause) {
		super(cause);
	}
}
