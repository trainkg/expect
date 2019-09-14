/*
 * 文件名称:          MailSenderJxkh.java
 * 版权所有@ 2013——2015 中科曙光信息技术无锡有限公司，保留所有权利
 * 时间:             2015年11月23日 下午2:25:19
 */
package org.barley.email;

import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 *  类注释
 * 
 * <p>
 * <p>
 * @项目        mail.service.mail
 * <p>
 * @作者:       华云佳
 * <p>
 * @日期:       2015年11月23日
 * <p>
 * @负责人:     华云佳
 * <p>
 * @负责小组:   
 * <p>
 * <p>
 */
public class MailSenderJxkh extends JavaMailSenderImpl
{
    private String charSet;
    private String from;
    /**
     * 获取编码格式
     * @return
     */
    public String getCharSet()
    {
        return charSet;
    }
    
    /**
     * 获取配置文件中的发件人信息
     * @return
     */
    public String getFrom()
    {
        return from;
    }

    public void setFrom(String from)
    {
        this.from = from;
    }

    public void setCharSet(String charSet)
    {
        this.charSet = charSet;
    }
    
    
}
