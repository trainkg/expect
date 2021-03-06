package com.barley.party.mappers;

import com.barley.party.modal.Employee;
import com.barley.party.vo.EmployeeCriteria;
import java.util.List;

public interface EmployeeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee
     *
     * @mbg.generated
     */
    //插入方法
    int insert(Employee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee
     *
     * @mbg.generated
     */
    int insertSelective(Employee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee
     *
     * @mbg.generated
     */
    List<Employee> searchByCriteria(EmployeeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee
     *
     * @mbg.generated
     */
    Employee selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Employee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_employee
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Employee record);
}