
package org.barley.exception;

import org.barley.Errorcode;

public class BarleyException extends RuntimeException implements Errorcode {

	private static final long serialVersionUID = -2779970763677402685L;

	/**
	 * EXCEPTION ERROR CODE;
	 */
	private String errorCode;

	public BarleyException() {
		super();
	}

	public BarleyException(String message, Throwable cause, String code) {
		super(message, cause);
		this.errorCode = code;
	}

	public BarleyException(String message, String code) {
		super(message);
		this.errorCode = code;
	}

	public BarleyException(Throwable cause, String code) {
		super(cause);
		this.errorCode = code;
	}

	public BarleyException(String message, Throwable cause) {
		super(message, cause);
	}

	public BarleyException(String message) {
		super(message);
	}

	public BarleyException(Throwable cause) {
		super(cause);
	}

	public String getErrorCode() {
		return this.errorCode;
	}

}
