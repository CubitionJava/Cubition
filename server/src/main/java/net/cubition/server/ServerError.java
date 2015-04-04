package net.cubition.server;

/**
 * This class represents a generic ServerError
 */
public class ServerError extends Error {

	private static final long serialVersionUID = 1L;

	/**
	 * Internal Error
	 */
	public ServerError() {
		super("Internal Error");
	}

	/**
	 * Internal Error with Reason
	 *
	 * @param message
	 *            Reason for Error
	 */
	public ServerError(String message) {
		super(message);
	}

	/**
	 * Internal Error with Cause
	 *
	 * @param cause
	 *            Cause of Error
	 */
	public ServerError(Throwable cause) {
		super("Internal Error", cause);
	}

	/**
	 * Internal Error with Reason and Cause
	 *
	 * @param message
	 *            Reason for Error
	 * @param cause
	 *            Cause of Error
	 */
	public ServerError(String message, Throwable cause) {
		super(message, cause);
	}
}
