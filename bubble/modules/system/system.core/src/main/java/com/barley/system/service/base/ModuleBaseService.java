package com.barley.system.service.base;

import com.barley.system.modal.Module;
import com.barley.system.service.base.searchvo.ModuleSearchVO;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * Auto generate , don't modify this file.
 * @author peculiar.1@163.com
 * @version $ID: com.barley.system.service.base.ModuleBaseService create date 2021-01-03 11:09:28
 */
public interface ModuleBaseService {
    Module create(Module record);

    void delete(Integer keyId);

    Module update(Module record);

    List<Module> findAll();

    Module findByPrimaryKey(Integer keyId);

    List<Module> searchByVO(ModuleSearchVO searchVO);

    PageInfo<Module> searchByVO(ModuleSearchVO searchVO, int page, int pageSize);
}