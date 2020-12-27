package com.barley.party.service;

import com.barley.party.modal.Department;
import com.barley.party.service.searchvo.DepartmentSearchVO;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * Auto generate , don't modify this file.
 * @author peculiar.1@163.com
 * @version $ID: com.barley.party.service.DepartmentBaseService create date 2020-12-27 14:50:38
 */
public interface DepartmentBaseService {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_department
     *
     * @mbg.generated
     */
    Department create(Department record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_department
     *
     * @mbg.generated
     */
    void delete(Long keyId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_department
     *
     * @mbg.generated
     */
    Department update(Department record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_department
     *
     * @mbg.generated
     */
    List<Department> findAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_department
     *
     * @mbg.generated
     */
    Department findByPrimaryKey(Long keyId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_department
     *
     * @mbg.generated
     */
    List<Department> searchByVO(DepartmentSearchVO searchVO);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_department
     *
     * @mbg.generated
     */
    PageInfo<Department> searchByVO(DepartmentSearchVO searchVO, int page, int pageSize);
}