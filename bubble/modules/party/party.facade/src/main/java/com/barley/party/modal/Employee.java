package com.barley.party.modal;

import java.io.Serializable;
import org.barley.bo.AutomaticEntityImpl;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table t_employee
 */
public class Employee extends AutomaticEntityImpl implements Serializable {
    /**
     * Database Column Remarks:
     *   系统用户
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_employee.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * Database Column Remarks:
     *   邮箱地址
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_employee.ename
     *
     * @mbg.generated
     */
    private String ename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_employee
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_employee.id
     *
     * @return the value of t_employee.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_employee.id
     *
     * @param id the value for t_employee.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_employee.ename
     *
     * @return the value of t_employee.ename
     *
     * @mbg.generated
     */
    public String getEname() {
        return ename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_employee.ename
     *
     * @param ename the value for t_employee.ename
     *
     * @mbg.generated
     */
    public void setEname(String ename) {
        this.ename = ename == null ? null : ename.trim();
    }
}