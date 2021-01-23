
package org.barley.utils;

public class ValidatorU {
	/**
	 * 数字正则
	 * 
	 * @author jihailong
	 */
	public static final String SZ_ZZ = "^[0-9]*$";

	/**
	 * 整数正则
	 * 
	 * @author jihailong
	 */
	public static final String ZS_ZZ = "^-?\\d+$";

	/**
	 * 浮点数正则
	 * 
	 * @author jihailong
	 */
	public static final String FDS_ZZ = "^(-?\\d+)(\\.\\d+)?";

	/**
	 * 非负浮点数正则（正浮点数 + 0）
	 * 
	 * @author jihailong
	 */
	public static final String FF_FDS_ZZ = "^\\d+(\\.\\d+)?$";

	/**
	 * 正浮点数正则
	 * 
	 * @author jihailong
	 */
	public static final String Z_FDS_ZZ = "^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$";

	/**
	 * 负浮点数正则
	 * 
	 * @author jihailong
	 */
	public static final String F_FDS_ZZ = "^(-(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*)))$";

	/**
	 * 判断一个字符窜是否符合某种正则表达式
	 * 
	 * @param value 判断的对象
	 * @param type  正则表达式
	 * @return
	 * @author jihailong
	 */
	public static boolean isMatches(String value, String type) {
		if (value != null && type != null && value.matches(type)) {
			return true;
		}
		return false;
	}

}
