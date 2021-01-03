package org.barley.exception;

/**
 * 
 * use 3 (type) + 8 (code) model
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: ExceptionCode.java, V1.0.0 2020年12月31日 上午11:40:37 $
 */
public interface ExceptionCode {

	enum ExceptionType {
		/**
		 * 
		 * Application error
		 * 
		 */
		ERR,

		/**
		 * 
		 * Business excetption
		 * 
		 */
		BIZ
	}

	/**
	 * illegal request.
	 */
	public static final String ERR_ILLEGAL_REQUEST = "ERR00000001";
}
