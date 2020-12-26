package org.barley.email;


/**
 *  类注释
 * 
 * <p>
 * <p>
 * @项目        mail.api.mail
 * <p>
 * @作者:       华云佳
 * <p>
 * @日期:       2015年11月20日
 * <p>
 * @负责人:     华云佳
 * <p>
 * @负责小组:   
 * <p>
 * <p>
 */
public interface IMailService
{
    /**
     * 发送邮件
     * @param mail
     */
    public void sendMail(Mail mail);

    /**
     * 绩效考核审批邮件通知
     * @param content
     * @param url
     * @param idCode
     */
    public void sentMail(String content, String url, String idCode);
}
