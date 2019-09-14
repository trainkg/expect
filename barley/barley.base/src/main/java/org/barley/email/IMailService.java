/*
 * 文件名称:          IMailService.java
 * 版权所有@ 2013——2015 中科曙光信息技术无锡有限公司，保留所有权利
 * 时间:             2015年11月20日 下午3:31:06
 */
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
