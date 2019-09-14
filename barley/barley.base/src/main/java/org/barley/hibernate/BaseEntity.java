/*
 * 文件名称:          BaseEntity.java
 * 版权所有@ 2013-2014 无锡城市云计算中心有限公司，保留所有权利
 * 编译器:           JDK1.7.0_25
 * 时间:             上午10:24:55
 */

package org.barley.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * 基础类 提供序列化实现 和  序列号 
 * <p>
 * <p>
 * @版本:       common
 * <p>
 * @作者:       zhuyy
 * <p>
 * @日期:       2015年5月26日
 * <p>
 * @负责人:     zhuyy
 * <p>
 * @负责小组:   commons
 * <p>
 * <p>
 */

@ MappedSuperclass
public class BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;
    @ Id
    @ Column(name = "id", unique = true, nullable = false, length = 32)
    @ GeneratedValue(generator = "uuid")
    @ GenericGenerator(name = "uuid", strategy = "uuid.hex")
    private String id;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

}
