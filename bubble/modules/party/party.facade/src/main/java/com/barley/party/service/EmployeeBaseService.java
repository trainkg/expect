package com.barley.party.service;

import com.barley.party.modal.Employee;
import com.barley.party.service.searchvo.EmployeeSearchVO;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * Auto generate , don't modify this file.
 * @author peculiar.1@163.com
 * @version $ID: com.barley.party.service.EmployeeBaseService create date 2020-12-27 15:11:34
 */
public interface EmployeeBaseService {
    Employee create(Employee record);

    void delete(Long keyId);

    Employee update(Employee record);

    List<Employee> findAll();

    Employee findByPrimaryKey(Long keyId);

    List<Employee> searchByVO(EmployeeSearchVO searchVO);

    PageInfo<Employee> searchByVO(EmployeeSearchVO searchVO, int page, int pageSize);
}