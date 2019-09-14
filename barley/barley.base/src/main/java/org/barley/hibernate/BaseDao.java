/*
 * 文件名称:          BaseDao.java
 * 版权所有@ 2014——2015 KISUN
 * 时间:             2014年11月20日 下午2:08:52
 */

package org.barley.hibernate;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 *  Hibernat DAO泛型基类扩展
 * 
 * <p>
 * <p>
 * @项目        hibernate-bridge
 * <p>
 * @作者:       KISUN
 * <p>
 * @日期:       2014年11月20日
 * <p>
 * @负责人:     KISUN
 * <p>
 * @负责小组:   
 * <p>
 * <p>
 */
public class BaseDao<T, PK extends Serializable> extends HibernateDao<T, PK>
{
    /**
     * 根据ID数组删除所有数据
     * 
     * @param cusIds
     */
    public void deleteAll(Class<T> entityClass, String[] ids)
    {
        String hql = "delete from " + entityClass.getSimpleName() + " where id in (:ids) ";
        Query query = createQuery(hql);
        query.setParameterList("ids", ids);
        query.executeUpdate();
    }

    /**
     * 保存集合中的所有对象
     * 
     * @param entities
     */
    public void saveAll(final Collection<T> entities)
    {
        Session session = getSession();
        for (T entity : entities)
        {
            session.saveOrUpdate(entity);
        }
    }

    /**
     * 根据数组查询一批数据
     * 
     * @param clss
     * @param paramName 字段名称
     * @param ids       字段条件
     * @return
     * @throws DaoException
     */
    @ SuppressWarnings("unchecked")
    public List<T> findAllByIds(Class<T> clss, String paramName, String[] ids)
    {
        String hql = " from " + clss.getSimpleName() + " where " + paramName + " in (:ids) ";
        Query query = createQuery(hql);
        query.setParameterList("ids", ids);
        List<T> list = query.list();
        return list;
    }

    /**
     * 判断是否存在同名
     * 
     * @param clss
     * @param paramName
     * @param param
     * @return
     */
    public Boolean isExistSameName(Class<T> clss, String paramName, Serializable param)
    {
        String hql = " from " + clss.getSimpleName() + " where " + paramName + " = ? ";
        List<T> list = find(hql, param);
        if (list == null || list.size() == 0)
        {
            return false;
        }
        return true;
    }

}
