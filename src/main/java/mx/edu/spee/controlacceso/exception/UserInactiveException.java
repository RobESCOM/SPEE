package mx.edu.spee.controlacceso.exception;

public class UserInactiveException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1195850721139275753L;

	public UserInactiveException() {
		super();
	}
	
	public UserInactiveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserInactiveException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserInactiveException(String message) {
		super(message);
	}

	public UserInactiveException(Throwable cause) {
		super(cause);
	}

}
