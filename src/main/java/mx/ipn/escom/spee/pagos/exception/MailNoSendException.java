package mx.ipn.escom.spee.pagos.exception;

public class MailNoSendException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MailNoSendException() {
		super();
	}

	public MailNoSendException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MailNoSendException(String message, Throwable cause) {
		super(message, cause);
	}

	public MailNoSendException(String message) {
		super(message);
	}

	public MailNoSendException(Throwable cause) {
		super(cause);
	}

}

