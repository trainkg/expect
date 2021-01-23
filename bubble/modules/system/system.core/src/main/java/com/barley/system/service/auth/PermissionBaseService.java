package com.barley.system.service.auth;

import com.barley.system.modal.Permission;
import com.barley.system.service.auth.searchvo.PermissionSearchVO;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * Auto generate , don't modify this file.
 * @author peculiar.1@163.com
 * @version $ID: com.barley.system.service.auth.PermissionBaseService create date 2021-01-03 11:09:28
 */
public interface PermissionBaseService {
    Permission create(Permission record);

    void delete(Integer keyId);

    Permission update(Permission record);

    List<Permission> findAll();

    Permission findByPrimaryKey(Integer keyId);

    List<Permission> searchByVO(PermissionSearchVO searchVO);

    PageInfo<Permission> searchByVO(PermissionSearchVO searchVO, int page, int pageSize);
}