package com.barley.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.barley.config.mappers.ModuleMapper;
import com.barley.config.modal.Module;
import com.barley.config.services.ModuleService;
import com.barley.config.vo.ModuleSearchVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 模块管理
 * @author peculiar.1@163.com
 * @version $ID: MuduleServiceImpl.java, V1.0.0 2020年5月10日 下午4:02:06 $
 */
@Service
@Transactional
public class MuduleServiceImpl implements ModuleService {

    @Override
    public Module findModule(Integer listId) {
        Assert.notNull(listId,"List Id is null");
        return dapModule.selectByPrimaryKey(listId);
    }
    
    @Override
    public PageInfo<Module> findModule(ModuleSearchVO searchVO, int page, int pageSize) {
        Page<Object> pagesvo = PageHelper.startPage(page, pageSize);
        List<Module> list = dapModule.selectByExample(searchVO);
        PageInfo<Module> pageInfo = new PageInfo<Module>(list,pageSize);
        pageInfo.setTotal(pagesvo.getTotal());
        return pageInfo;
    }
    
    
    @Autowired
    private ModuleMapper dapModule; 
}
