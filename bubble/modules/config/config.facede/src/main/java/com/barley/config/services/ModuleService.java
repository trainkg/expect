package com.barley.config.services;

import com.barley.config.modal.Module;
import com.barley.config.vo.ModuleSearchVO;
import com.github.pagehelper.PageInfo;

/**
 * @author peculiar.1@163.com
 * @version $ID: ModuleService.java, V1.0.0 2020年5月10日 下午3:58:19 $
 */
public interface ModuleService {
    public Module findModule(Integer listId);
    
    public PageInfo<Module> findModule(ModuleSearchVO searchVO, int page, int offNum);
}
