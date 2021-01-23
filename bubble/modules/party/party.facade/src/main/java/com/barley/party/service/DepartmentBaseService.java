package com.barley.party.service;

import com.barley.party.modal.Department;
import com.barley.party.service.searchvo.DepartmentSearchVO;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * Auto generate , don't modify this file.
 * @author peculiar.1@163.com
 * @version $ID: com.barley.party.service.DepartmentBaseService create date 2020-12-27 15:11:34
 */
public interface DepartmentBaseService {
    Department create(Department record);

    void delete(Long keyId);

    Department update(Department record);

    List<Department> findAll();

    Department findByPrimaryKey(Long keyId);

    List<Department> searchByVO(DepartmentSearchVO searchVO);

    PageInfo<Department> searchByVO(DepartmentSearchVO searchVO, int page, int pageSize);
}