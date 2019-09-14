/*
 * 文件名称:          NumberU.java
 * 版权所有@ 2014——2015 KISUN
 * 时间:             2015年5月18日 上午11:03:48
 */

package org.barley.utils;

import java.text.NumberFormat;

/**
 * 数字转化
 * 
 * <p>
 * <p>
 * 
 * @项目 petrel.net
 *     <p>
 * @作者: jihailong
 *      <p>
 * @日期: 2015年5月18日
 *      <p>
 * @负责人: KISUN
 *       <p>
 * @负责小组: <p>
 *        <p>
 */
public class NumberU
{

    /**
     * 数字类型转化
     * 
     * @param value
     *            被转化对象
     * @param i
     *            保留小数位数
     * @return
     */
    public static String numberFormatAs(Number value, Integer i)
    {
        if (i == null || i < 0)
        {
            i = 0;
        }
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(i);
        nf.setMinimumFractionDigits(i);
        nf.setGroupingUsed(false);
        try
        {
            return nf.format(value);
        }
        catch(Exception e)
        {
            return "";
        }
    }

    /**
     * 数字类型转化
     * 
     * @param value        被转化对象
     * @param i            保留小数位数
     * @param minNeed      最少小数位数保留与否
     * @return
     */
    public static String numberFormatAs(Number value, Integer i, boolean minNeed)
    {
        if (i == null || i < 0)
        {
            i = 0;
        }
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(i);
        if (minNeed)
        {
            nf.setMinimumFractionDigits(i);
        }
        nf.setGroupingUsed(false);
        try
        {
            return nf.format(value);
        }
        catch(Exception e)
        {
            return "";
        }
    }
}
