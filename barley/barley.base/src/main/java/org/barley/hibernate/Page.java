
package org.barley.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 与具体ORM实现无关的分页参数及查询结果封装.
 * 
 * 注意所有序号从1开始.
 * 
 * @param <T>
 *            Page中记录的类型.
 */
public class Page<T>
{
    // -- 公共变量 --//
    public static final String ASC = "asc";
    public static final String DESC = "desc";

    // -- 分页参数 --//
    // 使用原始类型的时候会造成参数绑定错误  当为空字符串的时候。
    protected Integer p = 1;
    protected Integer pindex = 1;
    protected Integer pageSize = 15;
    protected String orderBy = null;
    protected String order = null;
    // JPA 实现有已有条件加载出count(*) 暂时没有想到实现方法 。 需求autoCount的功能目前也不是太多。
    protected boolean autoCount = true;
    /**
     * 是否需要分页查询
     */
    protected boolean needPage = true;

    // -- 返回结果 --//
    protected List<T> result = new ArrayList<T>();
    protected long totalCount = -1;

    // -- 构造函数 --//
    public Page()
    {
    }

    public Page(int pageSize)
    {
        this.pageSize = pageSize;
    }

    // -- 分页参数访问函数 --//
    /**
     * 获得当前页的页号,序号从1开始,默认为1.
     */
    public int getP()
    {
        return p;
    }

    /**
     * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
     */
    public void setP(final Integer p)
    {
        if (p == null)
        {
            this.p = 1;
            return;
        }
        this.p = p;

        if (p < 1)
        {
            this.p = 1;
        }
    }

    /**
     * 返回Page对象自身的setPageNo函数,可用于连续设置。
     */
    /*public Page<T> p(final int thePageNo)
    {
        setP(thePageNo);
        return this;
    }*/

    /**
     * 获得每页的记录数量, 默认为-1.
     */
    public int getPageSize()
    {
        return pageSize;
    }

    /**
     * 设置每页的记录数量.
     */
    public void setPageSize(final int pageSize)
    {
        this.pageSize = pageSize;
    }

    /**
     * 返回Page对象自身的setPageSize函数,可用于连续设置。
     */
    public Page<T> pageSize(final int thePageSize)
    {
        setPageSize(thePageSize);
        return this;
    }

    /**
     * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从0开始.
     */
    public int getFirst()
    {
        return (p - 1) * pageSize;
    }

    /**
     * 获得排序字段,无默认值. 多个排序字段时用','分隔.
     */
    public String getOrderBy()
    {
        return orderBy;
    }

    /**
     * 设置排序字段,多个排序字段时用','分隔.
     */
    public void setOrderBy(final String orderBy)
    {
        this.orderBy = orderBy;
    }

    /**
     * 返回Page对象自身的setOrderBy函数,可用于连续设置。
     */
    public Page<T> orderBy(final String theOrderBy)
    {
        setOrderBy(theOrderBy);
        return this;
    }

    /**
     * 获得排序方向, 无默认值.
     */
    public String getOrder()
    {
        return order;
    }

    /**
     * 设置排序方式向.
     * 
     * @param order
     *            可选值为desc或asc,多个排序字段时用','分隔.
     */
    public void setOrder(final String order)
    {
        String lowcaseOrder = StringUtils.lowerCase(order);

        // 检查order字符串的合法值
        String[] orders = StringUtils.split(lowcaseOrder, ',');
        for (String orderStr : orders)
        {
            if (!StringUtils.equals(DESC, orderStr) && !StringUtils.equals(ASC, orderStr))
            {
                throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");
            }
        }

        this.order = lowcaseOrder;
    }

    /**
     * 返回Page对象自身的setOrder函数,可用于连续设置。
     */
    public Page<T> order(final String theOrder)
    {
        setOrder(theOrder);
        return this;
    }

    /**
     * 是否已设置排序字段,无默认值.
     */
    public boolean isOrderBySetted()
    {
        return (StringUtils.isNotBlank(orderBy) && StringUtils.isNotBlank(order));
    }

    /**
     * 获得查询对象时是否先自动执行count查询获取总记录数, 默认为false.
     */
    public boolean isAutoCount()
    {
        return autoCount;
    }

    /**
     * 设置查询对象时是否自动先执行count查询获取总记录数.
     */
    public void setAutoCount(final boolean autoCount)
    {
        this.autoCount = autoCount;
    }

    /**
     * 返回Page对象自身的setAutoCount函数,可用于连续设置。
     */
    public Page<T> autoCount(final boolean theAutoCount)
    {
        setAutoCount(theAutoCount);
        return this;
    }

    // -- 访问查询结果函数 --//

    /**
     * 获得页内的记录列表.
     */
    public List<T> getResult()
    {
        return result;
    }

    /**
     * 设置页内的记录列表.
     */
    public void setResult(final List<T> result)
    {
        this.result = result;
    }

    /**
     * 获得总记录数, 默认值为-1.
     */
    public long getTotalCount()
    {
        return totalCount;
    }

    /**
     * 设置总记录数.
     */
    public void setTotalCount(final long totalCount)
    {
        this.totalCount = totalCount;
    }

    /**
     * 根据pageSize与totalCount计算总页数, 默认值为-1.
     */
    public long getTotalPages()
    {
        if (totalCount < 0)
        {
            return -1;
        }

        long count = totalCount / pageSize;
        if (totalCount % pageSize > 0)
        {
            count++;
        }
        return count;
    }

    /**
     * 是否还有下一页.
     */
    public boolean isHasNext()
    {
        return (p + 1 <= getTotalPages());
    }

    /**
     * 取得下页的页号, 序号从1开始. 当前页为尾页时仍返回尾页序号.
     */
    public int getNextPage()
    {
        if (isHasNext())
        {
            return p + 1;
        }
        else
        {
            return p;
        }
    }

    /**
     * 是否还有上一页.
     */
    public boolean isHasPre()
    {
        return (p - 1 >= 1);
    }

    /**
     * 取得上页的页号, 序号从1开始. 当前页为首页时返回首页序号.
     */
    public int getPrePage()
    {
        if (isHasPre())
        {
            return p - 1;
        }
        else
        {
            return p;
        }
    }

    /**
     * @return the needPage
     */
    public boolean isNeedPage()
    {
        return needPage;
    }

    /**
     * @param needPage the needPage to set
     */
    public void setNeedPage(boolean needPage)
    {
        this.needPage = needPage;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }

    /**
     * get total result size
     * @return int
     */
    public int getCurPageSize()
    {
        if (getResult() != null)
        {
            return getResult().size();
        }
        else
        {
            return 0;
        }
    }
    
    /**
     * 初始化查询条件中的date 格式化, 实现类可以覆盖定义自己的格式
     * @return
     */
    public String getDatePattern()
    {
        return "yyyy-MM-dd HH:mm:ss";
    }
    
    /**
     * 计算当前查询的最大页码。
     * @return int
     */
    public int getMaxPage()
    {
        return (int)(totalCount + pageSize - 1) / pageSize;
    }

    public Integer getPindex()
    {
        return pindex;
    }

    public void setPindex(Integer pindex)
    {
        this.pindex = pindex;
        this.p = pindex;
    }
    
    

}
