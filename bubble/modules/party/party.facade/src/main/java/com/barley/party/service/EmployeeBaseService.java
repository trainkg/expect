package com.barley.party.service;

import com.barley.party.modal.Employee;
import com.barley.party.service.searchvo.EmployeeSearchVO;
import com.github.pagehelper.PageInfo;

/**
 * Auto generate , don't modify this file.
 * @author peculiar.1@163.com
 * @version $ID: com.barley.party.service.EmployeeBaseService create date 2020-12-25 13:03:26
 */
public interface EmployeeBaseService {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee
     *
     * @mbg.generated
     */
    Employee create(Employee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee
     *
     * @mbg.generated
     */
    void delete(Long keyId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee
     *
     * @mbg.generated
     */
    Employee update(Employee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee
     *
     * @mbg.generated
     */
    java.util.List<Employee> findAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee
     *
     * @mbg.generated
     */
    Employee findByPrimaryKey(Long keyId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee
     *
     * @mbg.generated
     */
    PageInfo<Employee> searchByVO(EmployeeSearchVO searchVO);
}