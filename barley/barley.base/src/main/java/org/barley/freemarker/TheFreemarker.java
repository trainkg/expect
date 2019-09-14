/*
 * 文件名称:          TheFreemarker.java
 * 版权所有@ 2013——2015 中科曙光信息技术无锡有限公司，保留所有权利
 * 时间:             2014年10月24日 上午10:28:33
 */

package org.barley.freemarker;

import java.io.File;
import java.io.IOException;

import freemarker.template.Template;

/**
 *  TheFreemarker
 * 
 * <p>
 * <p>
 * @项目        steering-file
 * <p>
 * @作者:       KISUN
 * <p>
 * @日期:       2014年10月24日
 * <p>
 * @负责人:     KISUN
 * <p>
 * @负责小组:   
 * <p>
 * <p>
 */
public class TheFreemarker extends AbstractFreemarker
{
    protected static final String TEMP = "temp";

    public TheFreemarker(@ SuppressWarnings("rawtypes") Class clazz, String pathPrefix)
        throws IOException
    {
        super(clazz, pathPrefix);
    }

    @ Override
    public void doSettings()
    {
        System.out.println("you can do something here");
    }

    public static void main(String[] args) throws Exception
    {
        TheFreemarker tf = new TheFreemarker(TheFreemarker.class, TEMP + File.separator);
        Template temp = null;
        try
        {
            temp = tf.configuration.getTemplate("test.ftl");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        if (temp == null)
        {
            throw new Exception("不存在的模板名称");
        }
        else
        {
            System.out.println(temp.getName());
            tf.doSettings();
        }
    }

}
