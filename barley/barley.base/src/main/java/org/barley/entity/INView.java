/*
 * 文件名称:          IdName.java
 * 版权所有@ 2013——2015 中科曙光信息技术无锡有限公司，保留所有权利
 * 时间:             2015年5月18日 下午1:02:14
 */

package org.barley.entity;

/**
 * 测试类
 * 
 * <p>
 * <p>
 * 
 * @项目 petrel.commonU
 *     <p>
 * @作者: jihailong
 *      <p>
 * @日期: 2015年5月18日
 *      <p>
 * @负责人: jihailong
 *       <p>
 * @负责小组: <p>
 *        <p>
 */
public class INView
{

    private String id;

    private String name;

    private Integer type;

    private int cc;

    public INView(String id, String name, Integer type, int cc)
    {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
        this.cc = cc;
    }

    public INView(String id, String name)
    {
        super();
        this.id = id;
        this.name = name;
    }

    public INView()
    {
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getType()
    {
        return type;
    }

    public void setType(Integer type)
    {
        this.type = type;
    }

    public int getCc()
    {
        return cc;
    }

    public void setCc(int cc)
    {
        this.cc = cc;
    }

}
