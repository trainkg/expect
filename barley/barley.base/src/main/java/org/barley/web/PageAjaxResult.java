/*
 * 文件名称:          PageAjaxResult.java
 * 版权所有@ 2013——2015 中科曙光信息技术无锡有限公司，保留所有权利
 * 时间:             2014-8-28 上午11:39:12
 */

package org.barley.web;



/**
 * PAGE 用于DATATABLE 插件整合使用
 * <p>
 * <p>
 * @项目        crm
 * <p>
 * @作者:       yangbao
 * <p>
 * @日期:       2014-8-28
 * <p>
 * @负责人:     yangbao
 * <p>
 * @负责小组:   
 * <p>
 * <p>
 */
public class PageAjaxResult extends AjaxResult
{
    /**
     * 当前页
     */
    private Integer page;

    /**
     * 总页数
     */
    private Integer recordsTotal;

    /**
     * 
     */
    private Integer recordsFiltered;

   
    /**
     * @return 返回 recordsTotal
     */
    public Integer getRecordsTotal()
    {
        return recordsTotal;
    }

    /**
     * @param recordsTotal 设置 recordsTotal
     */
    public void setRecordsTotal(Integer recordsTotal)
    {
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsTotal;
    }

    /**
     * @return 返回 recordsFiltered
     */
    public Integer getRecordsFiltered()
    {
        return recordsFiltered;
    }

    /**
     * @param recordsFiltered 设置 recordsFiltered
     */
    public void setRecordsFiltered(Integer recordsFiltered)
    {
        this.recordsFiltered = recordsFiltered;
    }

    public Integer getPage()
    {
        return page;
    }

    public void setPage(Integer page)
    {
        this.page = page;
    }

}
