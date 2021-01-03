package com.barley.config.service;

import com.barley.config.mappers.FormMapper;
import com.barley.config.modal.Form;
import com.barley.config.service.searchvo.FormSearchVO;
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
 * @version $ID: com.barley.config.service.FormBaseService create date 2020-12-31 13:35:23
 */
@Service
@Transactional
public class FormServiceImpl implements FormService {
    @Autowired
    protected FormMapper entityMapper;

    public Form create(Form record) {
        entityMapper.insert(record);
        return record;
    }

    public void delete(String keyId) {
        entityMapper.deleteByPrimaryKey(keyId);
    }

    public Form update(Form record) {
        entityMapper.updateByPrimaryKey(record);
        return record;
    }

    public List<Form> findAll() {
        FormSearchVO searchvo = new FormSearchVO();
        return entityMapper.searchByCriteria(searchvo);
    }

    public Form findByPrimaryKey(String keyId) {
        Form entity = entityMapper.selectByPrimaryKey(keyId);
        return entity;
    }

    public List<Form> searchByVO(FormSearchVO searchVO) {
        PageInfo<Form> pageInfo = internalfindBySearchVO(searchVO, null, null);
        return pageInfo.getList();
    }

    public PageInfo<Form> searchByVO(FormSearchVO searchVO, int page, int pageSize) {
        return internalfindBySearchVO(searchVO, page, pageSize);
    }

    protected PageInfo<Form> internalfindBySearchVO(FormSearchVO searchvo, Integer page, Integer pageSize) {
        Page<Object> pagesvo = null;
        if (page != null) {
            pagesvo = PageHelper.startPage(page, pageSize);
        }
        if (searchvo instanceof CriteriaBuilder) {
            ((CriteriaBuilder) searchvo).build();
        }
        List<Form> list = entityMapper.searchByCriteria(searchvo);
        PageInfo<Form> pageInfo = new PageInfo<Form>(list, pageSize);
        if (pagesvo != null) {
            pageInfo.setTotal(pagesvo.getTotal());
        }
        return pageInfo;
    }
}