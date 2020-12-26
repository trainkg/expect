package org.barley.email;

import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailSenderJxkh extends JavaMailSenderImpl {
	private String charSet;
	private String from;

	/**
	 * 获取编码格式
	 * 
	 * @return
	 */
	public String getCharSet() {
		return charSet;
	}

	/**
	 * 获取配置文件中的发件人信息
	 * 
	 * @return
	 */
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}

}
