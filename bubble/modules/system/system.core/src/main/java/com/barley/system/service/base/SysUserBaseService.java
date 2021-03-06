package com.barley.system.service.base;

import com.barley.system.modal.SysUser;
import com.barley.system.service.base.searchvo.SysUserSearchVO;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * Auto generate , don't modify this file.
 * @author peculiar.1@163.com
 * @version $ID: com.barley.system.service.base.SysUserBaseService
 */
public interface SysUserBaseService {
    SysUser create(SysUser record);

    void delete(String keyId);

    SysUser update(SysUser record);

    List<SysUser> findAll();

    SysUser findByPrimaryKey(String keyId);

    List<SysUser> searchByVO(SysUserSearchVO searchVO);

    PageInfo<SysUser> searchByVO(SysUserSearchVO searchVO, int page, int pageSize);
}