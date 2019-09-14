/*
 * 文件名称:          Errorcode.java
 * 版权所有@ 2013-2014 无锡城市云计算中心有限公司，保留所有权利
 * 编译器:           JDK1.7.0_25
 * 时间:             下午3:31:38
 */
package org.barley;

/**
 * ERROR CODE 
 * <p>
 * <p>
 * @版本:       CloudView V1.8
 * <p>
 * @作者:       zhuyy
 * <p>
 * @日期:       2014-4-15
 * <p>
 * @负责人:     zhuyy
 * <p>
 * @负责小组:   commons
 * <p>
 * <p>
 */
public interface Errorcode
{
    public static final String NOT_FOUND = "404";
    
    public static final String BUSSINESS_ERROR = "bussiness_error";
    
    public String getErrorCode();
}
