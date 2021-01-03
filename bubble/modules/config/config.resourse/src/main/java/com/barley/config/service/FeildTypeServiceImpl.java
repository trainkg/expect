package com.barley.config.service;

import com.barley.config.mappers.FeildTypeMapper;
import com.barley.config.modal.FeildType;
import com.barley.config.service.searchvo.FeildTypeSearchVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.barley.mybatis.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author peculiar.1@163.com
 * @version $ID: com.barley.config.service.FeildTypeBaseService create date 2020-12-31 13:35:23
 */
@Service
@Transactional
public class FeildTypeServiceImpl implements FeildTypeService {
    @Autowired
    protected FeildTypeMapper entityMapper;

    public FeildType create(FeildType record) {
        entityMapper.insert(record);
        return record;
    }

    public void delete(Short keyId) {
        entityMapper.deleteByPrimaryKey(keyId);
    }

    public FeildType update(FeildType record) {
        entityMapper.updateByPrimaryKey(record);
        return record;
    }

    public List<FeildType> findAll() {
        FeildTypeSearchVO searchvo = new FeildTypeSearchVO();
        return entityMapper.searchByCriteria(searchvo);
    }

    public FeildType findByPrimaryKey(Short keyId) {
        FeildType entity = entityMapper.selectByPrimaryKey(keyId);
        return entity;
    }

    public List<FeildType> searchByVO(FeildTypeSearchVO searchVO) {
        PageInfo<FeildType> pageInfo = internalfindBySearchVO(searchVO, null, null);
        return pageInfo.getList();
    }

    public PageInfo<FeildType> searchByVO(FeildTypeSearchVO searchVO, int page, int pageSize) {
        return internalfindBySearchVO(searchVO, page, pageSize);
    }

    protected PageInfo<FeildType> internalfindBySearchVO(FeildTypeSearchVO searchvo, Integer page, Integer pageSize) {
        Page<Object> pagesvo = null;
        if (page != null) {
            pagesvo = PageHelper.startPage(page, pageSize);
        }
        if (searchvo instanceof CriteriaBuilder) {
            ((CriteriaBuilder) searchvo).build();
        }
        List<FeildType> list = entityMapper.searchByCriteria(searchvo);
        PageInfo<FeildType> pageInfo = new PageInfo<FeildType>(list, pageSize);
        if (pagesvo != null) {
            pageInfo.setTotal(pagesvo.getTotal());
        }
        return pageInfo;
    }
}