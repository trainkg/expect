
package org.barley.exception;

import org.barley.Errorcode;

public class ServiceException extends RuntimeException implements Errorcode {

	private static final long serialVersionUID = -2779970763677402685L;

	/**
	 * EXCEPTION ERROR CODE;
	 */
	private String errorCode;

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable cause, String code) {
		super(message, cause);
		this.errorCode = code;
	}

	public ServiceException(String message, String code) {
		super(message);
		this.errorCode = code;
	}

	public ServiceException(Throwable cause, String code) {
		super(cause);
		this.errorCode = code;
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public String getErrorCode() {
		return this.errorCode;
	}

}
