package com.barley.config.codetable;

/**
 * CodeTable Exception
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: CodeTableException.java, V1.0.0 2020年6月27日 下午2:30:00 $
 */
public class CodeTableException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CodeTableException() {
		super();
	}

	public CodeTableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CodeTableException(String message, Throwable cause) {
		super(message, cause);
	}

	public CodeTableException(String message) {
		super(message);
	}

	public CodeTableException(Throwable cause) {
		super(cause);
	}
	
}
