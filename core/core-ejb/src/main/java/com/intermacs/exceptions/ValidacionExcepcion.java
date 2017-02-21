/**
 * 
 */
package com.intermacs.exceptions;

/**
 * @author eanunezt
 *
 */

@SuppressWarnings("serial")
public class ValidacionExcepcion extends Exception {

	/**
	 * 
	 */
	public ValidacionExcepcion() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a new exception with the specified detail message. The cause is not initialized, and may subsequently be initialized by a call to initCause.

Parameters:
message the detail message. The detail message is saved for later retrieval by the getMessage() method.
	 * @param message
	 */
	public ValidacionExcepcion(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a new exception with the specified cause and a detail message of (cause==null ? null : cause.toString()) (which typically contains the class and detail message of cause). This constructor is useful for exceptions that are little more than wrappers for other throwables (for example, java.security.PrivilegedActionException).

Parameters:
cause the cause (which is saved for later retrieval by the getCause() method). (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
Since:
1.4
	 * @param cause
	 */
	public ValidacionExcepcion(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a new exception with the specified detail message, cause, suppression enabled or disabled, and writable stack trace enabled or disabled.
	 * @param message
	 * @param cause
	 */
	public ValidacionExcepcion(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a new exception with the specified detail message, cause, suppression enabled or disabled, and writable stack trace enabled or disabled.
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ValidacionExcepcion(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
