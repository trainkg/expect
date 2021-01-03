package com.barley.config.service;

import com.barley.config.mappers.CodeTableMapper;
import com.barley.config.modal.CodeTable;
import com.barley.config.service.searchvo.CodeTableSearchVO;
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
 * @version $ID: com.barley.config.service.CodeTableBaseService create date 2020-12-31 13:35:23
 */
@Service
@Transactional
public class CodeTableServiceImpl implements CodeTableService {
    @Autowired
    protected CodeTableMapper entityMapper;

    public CodeTable create(CodeTable record) {
        entityMapper.insert(record);
        return record;
    }

    public void delete(Long keyId) {
        entityMapper.deleteByPrimaryKey(keyId);
    }

    public CodeTable update(CodeTable record) {
        entityMapper.updateByPrimaryKey(record);
        return record;
    }

    public List<CodeTable> findAll() {
        CodeTableSearchVO searchvo = new CodeTableSearchVO();
        return entityMapper.searchByCriteria(searchvo);
    }

    public CodeTable findByPrimaryKey(Long keyId) {
        CodeTable entity = entityMapper.selectByPrimaryKey(keyId);
        return entity;
    }

    public List<CodeTable> searchByVO(CodeTableSearchVO searchVO) {
        PageInfo<CodeTable> pageInfo = internalfindBySearchVO(searchVO, null, null);
        return pageInfo.getList();
    }

    public PageInfo<CodeTable> searchByVO(CodeTableSearchVO searchVO, int page, int pageSize) {
        return internalfindBySearchVO(searchVO, page, pageSize);
    }

    protected PageInfo<CodeTable> internalfindBySearchVO(CodeTableSearchVO searchvo, Integer page, Integer pageSize) {
        Page<Object> pagesvo = null;
        if (page != null) {
            pagesvo = PageHelper.startPage(page, pageSize);
        }
        if (searchvo instanceof CriteriaBuilder) {
            ((CriteriaBuilder) searchvo).build();
        }
        List<CodeTable> list = entityMapper.searchByCriteria(searchvo);
        PageInfo<CodeTable> pageInfo = new PageInfo<CodeTable>(list, pageSize);
        if (pagesvo != null) {
            pageInfo.setTotal(pagesvo.getTotal());
        }
        return pageInfo;
    }
}