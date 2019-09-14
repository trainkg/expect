/*
 * 文件名称:          PasswordUtil.java
 * 版权所有@ 2013——2015 中科曙光信息技术无锡有限公司，保留所有权利
 * 时间:             2014年9月4日 下午3:45:42
 */
package org.barley.web.utils;

/**
 *  密码工具类
 * 
 * <p>
 * <p>
 * @项目        edu.webapp
 * <p>
 * @作者:       jihailong
 * <p>
 * @日期:       2014年9月4日
 * <p>
 * @负责人:     jihailong
 * <p>
 * @负责小组:   common
 * <p>
 * <p>
 */
public enum PasswordUtil
{
    DEFAULT_RESET_PASSWORD("123456");

    private String pwd = "";

    /* 构造器*/
    private PasswordUtil(String str)
    {
        this.pwd = str;
    }
    
    /* 用这个方法代替ordinal()方法 */  
    public String toString()
    {
        return this.pwd;
    }
}
