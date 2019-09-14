/*
 * 文件名称:          AjaxResult.java
 * 版权所有@ 2013-2014 无锡城市云计算中心有限公司，保留所有权利
 * 编译器:           JDK1.7.0_25
 * 时间:             上午10:31:38
 */

package org.barley.web;

/**
 * AJAX 返回提示信息 
 * <p>
 * <p>
 * @版本:       CloudView V1.8
 * <p>
 * @作者:       zhuyy
 * <p>
 * @日期:       2014-6-16
 * <p>
 * @负责人:     zhuyy
 * <p>
 * @负责小组:   commons
 * <p>
 * <p>
 */
public class AjaxResult
{
    public static final String SUCCESS = "1";
    public static final String FAILED = "0";

    private String c;
    private String msg;
    private Object data;

    public AjaxResult()
    {
    }

    public AjaxResult(String code, String message)
    {
        this.c = code;
        this.msg = message;
    }

    public static AjaxResult newSucessResult(String msg)
    {
        return new AjaxResult(SUCCESS, msg);
    }

    public static AjaxResult newSucessResult(String msg, Object data)
    {
        AjaxResult ar = new AjaxResult();
        ar.msg = msg;
        ar.data = data;
        ar.c = SUCCESS;
        return ar;
    }

    public static AjaxResult newFailResult(String msg)
    {
        return new AjaxResult(FAILED, msg);
    }

    public String getC()
    {
        return c;
    }

    public void setC(String c)
    {
        this.c = c;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }
   
    public boolean getSuccess()
    {
        if (SUCCESS.equals(getC()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
}
