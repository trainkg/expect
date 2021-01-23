package org.barley.email;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class MailServiceImpl implements IMailService {

	public static final String MAIL_DES_KEY = "$ZHU_JXKH_BEIJING$";
	public static final String CODE_SUFFIX = "$$-JKXH-3FSDF4$$";
	public static final String VALID_TIME_PETTEN = "yyyyMMddHHmmss";
	private FreeMarkerConfigurer freeMarkerConfigurer;

	private MailSenderJxkh mailSenderJxkh;

	private boolean testModel = false;
	/**
	 * 测试模式接受人
	 */
	private String testTo = null;

	/**
	 * @return 返回 freeMarkerConfigurer
	 */
	public FreeMarkerConfigurer getFreeMarkerConfigurer() {
		return freeMarkerConfigurer;
	}

	/**
	 * @param freeMarkerConfigurer 设置 freeMarkerConfigurer
	 */
	public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
		this.freeMarkerConfigurer = freeMarkerConfigurer;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.sugon.jxkh.mail.service.services.IMailService#sendMail(com.sugon.jxkh.mail.domain.Mail)
	 * 
	 */
	@Async
	public void sendMail(Mail mail) {
		if (mail != null) {
			try {
				send(mailSenderJxkh, mail);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 发送邮件核心
	 * 
	 * @param mail
	 * @throws Exception
	 */
	private void send(MailSenderJxkh senderMail, Mail mail) throws Exception {
		MimeMessage mailMessage = senderMail.createMimeMessage();
		if (mail.isHtml()) {
			String from = mail.getFrom();
			if (StringUtils.isEmpty(from)) {
				mail.setFrom(senderMail.getFrom());
			}
			// 如果是富文本
			sendHTML(mailMessage, mail, senderMail.getCharSet());
		} else {
			// 基本文本
			sendTEXT(mailMessage, mail);
		}
		try {
			senderMail.send(mailMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建富文本
	 * 
	 * @param senderImpl
	 * @throws MessagingException
	 * @throws TemplateException
	 * @throws IOException
	 */
	private void sendHTML(MimeMessage mailMessage, Mail mail, String charSet)
			throws MessagingException, IOException, TemplateException {
		MimeMessageHelper htmlMimeMsg = new MimeMessageHelper(mailMessage, Boolean.TRUE, charSet);
		getHtmlTemp(mail.getHtmlParamsMap(), mail.getTempName(), htmlMimeMsg);
		htmlMimeMsg.setFrom(mail.getFrom());
		if (testModel) {
			htmlMimeMsg.setTo(testTo);
		} else {
			htmlMimeMsg.setTo(mail.getTo());
		}
		if (!testModel) {
			if (null != mail.getCc()) {
				htmlMimeMsg.setCc(mail.getCc());
			}
		}
		htmlMimeMsg.setSubject(mail.getTitle());
	}

	/**
	 * 读取页面模板
	 * 
	 * @param params
	 * @param tempName
	 * @param htmlMimeMsg
	 * @throws IOException
	 * @throws MessagingException
	 * @throws TemplateException
	 */
	private void getHtmlTemp(Map<String, Object> params, String tempName, MimeMessageHelper htmlMimeMsg)
			throws IOException, MessagingException, TemplateException {
		Template tpl = freeMarkerConfigurer.getConfiguration().getTemplate(tempName);
		htmlMimeMsg.setText(FreeMarkerTemplateUtils.processTemplateIntoString(tpl, params), true);
	}

	/**
	 * 发送普通文本
	 * 
	 * @param senderImpl
	 */
	private void sendTEXT(MimeMessage mailMessage, Mail mail) {
		MimeMailMessage mailMsg = new MimeMailMessage(mailMessage);
		if (testModel) {
			mailMsg.setTo(testTo);// 收件人
		} else {
			mailMsg.setTo(mail.getTo());// 收件人
		}
		mailMsg.setSubject(mail.getTitle());// 邮件主题
		mailMsg.setText(mail.getMessage());// 邮件内容
		mailMsg.setFrom(mail.getFrom());
		if (!testModel) {
			String[] ccs = mail.getCc();
			if (null != ccs) {
				mailMsg.setCc(ccs);
			}
		}
	}

	/**
	 * 绩效考核审批邮件通知
	 * 
	 * @param content
	 * @param url
	 * @param idCode
	 */
	@Async
	public void sentMail(String content, String url, String idCode) {
		Mail mail = new Mail();
		mail.setTitle("绩效考核审批通知");
		mail.setHtml(true);
		mail.setTempName(Mail.MailTempList.TEMP.getValue());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("content", content);
		if (!StringUtils.isEmpty(url)) {
			/*
			 * try { // 有效时间控制 Calendar c = Calendar.getInstance();
			 * c.add(Calendar.DAY_OF_MONTH, 7); SimpleDateFormat sdf = new
			 * SimpleDateFormat(VALID_TIME_PETTEN); url += "?jxtoken=" + URLEncoder.encode(
			 * DESUtil.encrypt( idCode + sdf.format(c.getTime()) + CODE_SUFFIX,
			 * MAIL_DES_KEY), "ASCII"); } catch (Exception e) { e.printStackTrace(); }
			 * params.put("lj", "<a href=" + url + ">点击查看</a>");
			 */}
		params.put("detail", "<a href=" + url + ">" + url + "</a> <p>注意：链接有效时间7天,链接不要发送给其他人</p>");
		// 测试环境
		// String[] to = new String[]{"shaoshuai@sugon.com"};
		// 生产环境
		String[] to = new String[] { idCode + "@sugon.com" };
		if (testModel) {
			mail.setTo(new String[] { testTo });// 收件人
		} else {
			mail.setTo(to);
		}
		mail.setHtmlParamsMap(params);

		// 发出
		sendMail(mail);
	}

	public MailSenderJxkh getMailSenderJxkh() {
		return mailSenderJxkh;
	}

	public void setMailSenderJxkh(MailSenderJxkh mailSenderJxkh) {
		this.mailSenderJxkh = mailSenderJxkh;
	}

	public void setTestModel(boolean testModel) {
		this.testModel = testModel;
	}

	public void setTestTo(String testTo) {
		this.testTo = testTo;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(
				URLEncoder.encode("?jxtoken=6pBVrh57KuJkGbib70i5mg+XiU4cR5TidiI9Ksxmeos5IjAafgycfQ==", "ASCII"));
	}

}
