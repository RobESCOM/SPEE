package mx.ipn.escom.spee.pagos.exception;

public class SinPagosConCorteException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SinPagosConCorteException() {
		super();
	}

	public SinPagosConCorteException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SinPagosConCorteException(String message, Throwable cause) {
		super(message, cause);
	}

	public SinPagosConCorteException(String message) {
		super(message);
	}

	public SinPagosConCorteException(Throwable cause) {
		super(cause);
	}

}
