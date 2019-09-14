
package org.barley.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.barley.hibernate.PropertyFilter.MatchType;
import org.barley.reflection.ReflectionUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.springframework.util.Assert;


/**
 * 封装SpringSide扩展功能的Hibernat DAO泛型基类.
 * 
 * 扩展功能包括分页查询,按属性过滤条件列表查询. 可在Service层直接使用,也可以扩展泛型DAO子类使用,见两个构造函数的注释.
 * 
 * @param <T>
 *            DAO操作的对象类型
 * @param <PK>
 *            主键类型
 */
public class HibernateDao<T, PK extends Serializable> extends SimpleHibernateDao<T, PK>
{
    /**
     * 用于Dao层子类的构造函数. 通过子类的泛型定义取得对象类型Class. eg. public class UserDao extends
     * HibernateDao<User, Long>{ }
     */
    public HibernateDao()
    {
        super();
    }

    /**
     * 用于省略Dao层, Service层直接使用通用HibernateDao的构造函数. 在构造函数中定义对象类型Class. eg.
     * HibernateDao<User, Long> userDao = new HibernateDao<User,
     * Long>(sessionFactory, User.class);
     */
    public HibernateDao(final SessionFactory sessionFactory, final Class<T> entityClass)
    {
        super(sessionFactory, entityClass);
    }

    // -- 分页查询函数 --//

    /**
     * 分页获取全部对象.
     */
    public Page<T> getAll(final Page<T> page)
    {
        page.setNeedPage(false);
        return findPage(page);
    }

    /**
     * 从查询HQL构建count(*)HQL<br> 查询条件请使用小写 不做大小写兼容处理, 不支持包含distinct的查询处理,请自行处理
     * 
     * @param orgHql
     * @return
     */
    public String prepareCountHql(String orgHql)
    {
        String fromHql = orgHql.replace("fetch", "");
        fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
        fromHql = StringUtils.substringBefore(fromHql, "order by");
        return "select count(*) " + fromHql;
    }

    /**
     * 
     * 
     * @param page
     *            分页参数.
     * @param c 提供用戶自己构造复杂条件查询
     * 
     * @return 分页查询结果.附带结果列表及所有查询输入参数.
     */
    @ SuppressWarnings("unchecked")
    public Page<T> findPage(final Page<T> page, final Criteria c)
    {
        Assert.notNull(page, "page不能为空");
        if (page.isAutoCount())
        {
            long totalCount = countCriteriaResult(c);
            page.setTotalCount(totalCount);
        }
        setPageParameterToCriteria(c, page);
        List<T> result = c.list();
        page.setResult(result);
        return page;
    }

    /**
     * 按Criteria分页查询. 只能用于构建简单查询。
     * 
     * @param page
     *            分页参数.
     * @param criterions
     *            数量可变的Criterion.
     * 
     * @return 分页查询结果.附带结果列表及所有查询输入参数.
     */
    @ SuppressWarnings("unchecked")
    public Page<T> findPage(final Page<T> page, final Criterion...criterions)
    {
        Assert.notNull(page, "page不能为空");

        Criteria c = createCriteria(criterions);

        if (page.isAutoCount())
        {
            long totalCount = countCriteriaResult(c);
            page.setTotalCount(totalCount);
        }

        setPageParameterToCriteria(c, page);

        List<T> result = c.list();
        page.setResult(result);
        return page;
    }

    /**
     * 设置分页参数到Query对象,辅助函数.
     */
    protected Query setPageParameterToQuery(final Query q, final Page<T> page)
    {

        Assert.isTrue(page.getPageSize() > 0, "Page Size must larger than zero");

        // hibernate的firstResult的序号从0开始
        q.setFirstResult(page.getFirst() - 1);
        q.setMaxResults(page.getPageSize());
        return q;
    }

    /**
     * 设置分页参数到Criteria对象,辅助函数.
     */
    protected Criteria setPageParameterToCriteria(final Criteria c, final Page<T> page)
    {

        Assert.isTrue(page.getPageSize() > 0, "Page Size must larger than zero");

        // hibernate的firstResult的序号从0开始
        if (page.isNeedPage())
        {
            c.setFirstResult(page.getFirst() - 1);
            c.setMaxResults(page.getPageSize());
        }

        if (page.isOrderBySetted())
        {
            String[] orderByArray = StringUtils.split(page.getOrderBy(), ',');
            String[] orderArray = StringUtils.split(page.getOrder(), ',');

            Assert.isTrue(orderByArray.length == orderArray.length, "分页多重排序参数中,排序字段与排序方向的个数不相等");

            for (int i = 0; i < orderByArray.length; i++)
            {
                if (Page.ASC.equals(orderArray[i]))
                {
                    c.addOrder(Order.asc(orderByArray[i]));
                }
                else
                {
                    c.addOrder(Order.desc(orderByArray[i]));
                }
            }
        }
        return c;
    }

    /**
     * 执行count查询获得本次Criteria查询所能获得的对象总数.
     */
    @ SuppressWarnings("unchecked")
    protected long countCriteriaResult(final Criteria c)
    {
        CriteriaImpl impl = (CriteriaImpl)c;

        // 先把Projection、ResultTransformer、OrderBy取出来,清空三者后再执行Count操作
        Projection projection = impl.getProjection();
        ResultTransformer transformer = impl.getResultTransformer();

        List<CriteriaImpl.OrderEntry> orderEntries = null;
        try
        {
            orderEntries = (List)ReflectionUtils.getFieldValue(impl, "orderEntries");
            ReflectionUtils.setFieldValue(impl, "orderEntries", new ArrayList());
        }
        catch(Exception e)
        {
            logger.error("不可能抛出的异常:{}", e.getMessage());
        }

        // 执行Count查询
        Long totalCountObject = (Long)c.setProjection(Projections.rowCount()).uniqueResult();
        long totalCount = (totalCountObject != null) ? totalCountObject : 0;

        // 将之前的Projection,ResultTransformer和OrderBy条件重新设回去
        c.setProjection(projection);

        if (projection == null)
        {
            c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        }
        if (transformer != null)
        {
            c.setResultTransformer(transformer);
        }
        try
        {
            ReflectionUtils.setFieldValue(impl, "orderEntries", orderEntries);
        }
        catch(Exception e)
        {
            logger.error("不可能抛出的异常:{}", e.getMessage());
        }

        return totalCount;
    }

    // -- 属性过滤条件(PropertyFilter)查询函数 --//

    /**
     * 按属性查找对象列表,支持多种匹配方式.
     * 
     * @param matchType
     *            匹配方式,目前支持的取值见PropertyFilter的MatcheType enum.
     */
    public List<T> findBy(final String propertyName, final Object value, final MatchType matchType)
    {
        Criterion criterion = buildCriterion(propertyName, value, matchType);
        return find(criterion);
    }

    /**
     * 按属性过滤条件列表查找对象列表.
     */
    public List<T> find(List<PropertyFilter> filters)
    {
        Criterion[] criterions = buildCriterionByPropertyFilter(filters);
        return find(criterions);
    }

    /**
     * 按属性过滤条件列表分页查找对象.
     */
    public Page<T> findPage(final Page<T> page, final List<PropertyFilter> filters)
    {
        Criterion[] criterions = buildCriterionByPropertyFilter(filters);
        return findPage(page, criterions);
    }

    /**
     * 按属性条件参数创建Criterion,辅助函数.
     */
    protected Criterion buildCriterion(final String propertyName, final Object propertyValue,
        final MatchType matchType)
    {
        Assert.hasText(propertyName, "propertyName不能为空");
        Criterion criterion = null;
        // 根据MatchType构造criterion
        switch (matchType)
        {
            case EQ:
                criterion = Restrictions.eq(propertyName, propertyValue);
                break;
            case LIKE:
                criterion = Restrictions.like(propertyName, (String)propertyValue,
                    MatchMode.ANYWHERE);
                break;

            case LE:
                criterion = Restrictions.le(propertyName, propertyValue);
                break;
            case LT:
                criterion = Restrictions.lt(propertyName, propertyValue);
                break;
            case GE:
                criterion = Restrictions.ge(propertyName, propertyValue);
                break;
            case GT:
                criterion = Restrictions.gt(propertyName, propertyValue);
        }
        return criterion;
    }

    /**
     * 按属性条件列表创建Criterion数组,辅助函数.
     */
    protected Criterion[] buildCriterionByPropertyFilter(final List<PropertyFilter> filters)
    {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        for (PropertyFilter filter : filters)
        {
            if (!filter.hasMultiProperties())
            { // 只有一个属性需要比较的情况.
                Criterion criterion = buildCriterion(filter.getPropertyName(),
                    filter.getMatchValue(), filter.getMatchType());
                criterionList.add(criterion);
            }
            else
            {// 包含多个属性需要比较的情况,进行or处理.
                Disjunction disjunction = Restrictions.disjunction();
                for (String param : filter.getPropertyNames())
                {
                    Criterion criterion = buildCriterion(param, filter.getMatchValue(),
                        filter.getMatchType());
                    disjunction.add(criterion);
                }
                criterionList.add(disjunction);
            }
        }
        return criterionList.toArray(new Criterion[criterionList.size()]);
    }
}
