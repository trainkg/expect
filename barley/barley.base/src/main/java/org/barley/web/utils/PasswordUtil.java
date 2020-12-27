
package org.barley.web.utils;

public enum PasswordUtil {
	DEFAULT_RESET_PASSWORD("123456");

	private String pwd = "";

	/* 构造器 */
	private PasswordUtil(String str) {
		this.pwd = str;
	}

	/* 用这个方法代替ordinal()方法 */
	public String toString() {
		return this.pwd;
	}
}
