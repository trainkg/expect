package com.barley.system.modal;

import java.io.Serializable;
import org.barley.bo.AutomaticEntityImpl;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table t_module
 */
public class Module extends AutomaticEntityImpl implements Serializable {
    /**
     * Database Column Remarks:
     *   list id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_module.List_id
     *
     * @mbg.generated
     */
    private Integer listId;

    /**
     * Database Column Remarks:
     *   父modal id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_module.parent_id
     *
     * @mbg.generated
     */
    private Integer parentId;

    /**
     * Database Column Remarks:
     *   模块名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_module.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     * Database Column Remarks:
     *   URI 路径
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_module.uri
     *
     * @mbg.generated
     */
    private String uri;

    /**
     * Database Column Remarks:
     *   模块描述
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_module.describe
     *
     * @mbg.generated
     */
    private String describe;

    /**
     * Database Column Remarks:
     *   0/1
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_module.active
     *
     * @mbg.generated
     */
    private Short active;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_module.order
     *
     * @mbg.generated
     */
    private Short order;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_module.biz_modular_id
     *
     * @mbg.generated
     */
    private Integer bizModularId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_module
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_module.List_id
     *
     * @return the value of t_module.List_id
     *
     * @mbg.generated
     */
    public Integer getListId() {
        return listId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_module.List_id
     *
     * @param listId the value for t_module.List_id
     *
     * @mbg.generated
     */
    public void setListId(Integer listId) {
        this.listId = listId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_module.parent_id
     *
     * @return the value of t_module.parent_id
     *
     * @mbg.generated
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_module.parent_id
     *
     * @param parentId the value for t_module.parent_id
     *
     * @mbg.generated
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_module.name
     *
     * @return the value of t_module.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_module.name
     *
     * @param name the value for t_module.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_module.uri
     *
     * @return the value of t_module.uri
     *
     * @mbg.generated
     */
    public String getUri() {
        return uri;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_module.uri
     *
     * @param uri the value for t_module.uri
     *
     * @mbg.generated
     */
    public void setUri(String uri) {
        this.uri = uri == null ? null : uri.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_module.describe
     *
     * @return the value of t_module.describe
     *
     * @mbg.generated
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_module.describe
     *
     * @param describe the value for t_module.describe
     *
     * @mbg.generated
     */
    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_module.active
     *
     * @return the value of t_module.active
     *
     * @mbg.generated
     */
    public Short getActive() {
        return active;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_module.active
     *
     * @param active the value for t_module.active
     *
     * @mbg.generated
     */
    public void setActive(Short active) {
        this.active = active;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_module.order
     *
     * @return the value of t_module.order
     *
     * @mbg.generated
     */
    public Short getOrder() {
        return order;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_module.order
     *
     * @param order the value for t_module.order
     *
     * @mbg.generated
     */
    public void setOrder(Short order) {
        this.order = order;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_module.biz_modular_id
     *
     * @return the value of t_module.biz_modular_id
     *
     * @mbg.generated
     */
    public Integer getBizModularId() {
        return bizModularId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_module.biz_modular_id
     *
     * @param bizModularId the value for t_module.biz_modular_id
     *
     * @mbg.generated
     */
    public void setBizModularId(Integer bizModularId) {
        this.bizModularId = bizModularId;
    }
}