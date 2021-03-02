package com.barley.system.service.base;

import java.util.List;

import org.barley.mybatis.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barley.system.mappers.ModuleMapper;
import com.barley.system.modal.Module;
import com.barley.system.service.base.searchvo.ModuleSearchVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author peculiar.1@163.com
 * @version $ID: com.barley.system.service.base.ModuleBaseService create date 2020-12-30 21:58:50
 */
@Service
@Transactional
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    protected ModuleMapper entityMapper;

    public Module create(Module record) {
        entityMapper.insert(record);
        return record;
    }

    public void delete(Integer keyId) {
        entityMapper.deleteByPrimaryKey(keyId);
    }

    public Module update(Module record) {
        entityMapper.updateByPrimaryKey(record);
        return record;
    }

    public List<Module> findAll() {
        ModuleSearchVO searchvo = new ModuleSearchVO();
        return entityMapper.searchByCriteria(searchvo);
    }

    public Module findByPrimaryKey(Integer keyId) {
        Module entity = entityMapper.selectByPrimaryKey(keyId);
        return entity;
    }

    public List<Module> searchByVO(ModuleSearchVO searchVO) {
        PageInfo<Module> pageInfo = internalfindBySearchVO(searchVO, null, null);
        return pageInfo.getList();
    }

    public PageInfo<Module> searchByVO(ModuleSearchVO searchVO, int page, int pageSize) {
        return internalfindBySearchVO(searchVO, page, pageSize);
    }

    protected PageInfo<Module> internalfindBySearchVO(ModuleSearchVO searchvo, Integer page, Integer pageSize) {
        Page<Object> pagesvo = null;
        if (page != null) {
            pagesvo = PageHelper.startPage(page, pageSize);
        }
        if (searchvo instanceof CriteriaBuilder) {
            ((CriteriaBuilder) searchvo).build();
        }
        List<Module> list = entityMapper.searchByCriteria(searchvo);
        PageInfo<Module> pageInfo = null;
        if(pageSize != null) {
            pageInfo = new PageInfo<Module>(list, pageSize);
            if (pagesvo != null) {
                pageInfo.setTotal(pagesvo.getTotal());
            }
        }else {
            pageInfo = new PageInfo<Module>(list);
        }
        return pageInfo;
    }
}