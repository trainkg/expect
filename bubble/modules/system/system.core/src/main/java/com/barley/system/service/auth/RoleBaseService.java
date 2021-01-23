package com.barley.system.service.auth;

import com.barley.system.modal.Role;
import com.barley.system.service.auth.searchvo.RoleSearchVO;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * Auto generate , don't modify this file.
 * @author peculiar.1@163.com
 * @version $ID: com.barley.system.service.auth.RoleBaseService create date 2021-01-03 11:09:28
 */
public interface RoleBaseService {
    Role create(Role record);

    void delete(Integer keyId);

    Role update(Role record);

    List<Role> findAll();

    Role findByPrimaryKey(Integer keyId);

    List<Role> searchByVO(RoleSearchVO searchVO);

    PageInfo<Role> searchByVO(RoleSearchVO searchVO, int page, int pageSize);
}