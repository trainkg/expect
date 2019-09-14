package org.barley.email;

import java.io.Serializable;
import java.util.Map;

/**
 * 类注释
 * 
 * <p>
 * <p>
 * 
 * @项目 mail.api.mail
 *     <p>
 * @作者: 华云佳
 *      <p>
 * @日期: 2015年11月20日
 *      <p>
 * @负责人: 华云佳
 *       <p>
 * @负责小组: <p>
 *        <p>
 */
public class Mail implements Serializable {
	/**
     * 
     */
	private static final long serialVersionUID = -3481041523992174242L;

	/**
	 * 收件人列表
	 */
	private String[] to;

	/**
	 * 抄送人列表
	 */
	private String[] cc;

	/**
	 * 邮件标题
	 */
	private String title;

	/**
	 * 邮件主题内容
	 */
	private String message;

	/**
	 * 是否富文本
	 */
	private boolean isHtml;

	/**
	 * 模板
	 */
	private String tempName;

	/**
	 * 发件人 允许为空，如果为空，则从配置文件中读取发件人
	 */
	private String from;

	/**
	 * 富文本参数
	 */
	private Map<String, Object> htmlParamsMap;

	public enum MailTempList {
		TEMP("mailTemp.ftl");

		private final String value;

		MailTempList(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public Mail() {
	}

	/**
	 * @return 返回 to
	 */
	public String[] getTo() {
		return to;
	}

	/**
	 * @param to
	 *            设置 to
	 */
	public void setTo(String[] to) {
		this.to = to;
	}

	/**
	 * @return 返回 cc
	 */
	public String[] getCc() {
		return cc;
	}

	/**
	 * @param cc
	 *            设置 cc
	 */
	public void setCc(String[] cc) {
		this.cc = cc;
	}

	/**
	 * @return 返回 title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            设置 title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return 返回 message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            设置 message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return 返回 isHtml
	 */
	public boolean isHtml() {
		return isHtml;
	}

	/**
	 * @param isHtml
	 *            设置 isHtml
	 */
	public void setHtml(boolean isHtml) {
		this.isHtml = isHtml;
	}

	/**
	 * @return 返回 tempName
	 */
	public String getTempName() {
		return tempName;
	}

	/**
	 * @param tempName
	 *            设置 tempName
	 */
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	/**
	 * @return 返回 htmlParamsMap
	 */
	public Map<String, Object> getHtmlParamsMap() {
		return htmlParamsMap;
	}

	/**
	 * @param htmlParamsMap
	 *            设置 htmlParamsMap
	 */
	public void setHtmlParamsMap(Map<String, Object> htmlParamsMap) {
		this.htmlParamsMap = htmlParamsMap;
	}

	/**
	 * @return 返回 from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            设置 from
	 */
	public void setFrom(String from) {
		this.from = from;
	}
}
