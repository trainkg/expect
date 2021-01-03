package com.barley.system.service.base;

import com.barley.system.mappers.BizModularMapper;
import com.barley.system.modal.BizModular;
import com.barley.system.service.base.searchvo.BizModularSearchVO;
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
 * @version $ID: com.barley.system.service.base.BizModularBaseService create date 2020-12-30 21:58:50
 */
@Service
@Transactional
public class BizModularServiceImpl implements BizModularService {
    @Autowired
    protected BizModularMapper entityMapper;

    public BizModular create(BizModular record) {
        entityMapper.insert(record);
        return record;
    }

    public void delete(Integer keyId) {
        entityMapper.deleteByPrimaryKey(keyId);
    }

    public BizModular update(BizModular record) {
        entityMapper.updateByPrimaryKey(record);
        return record;
    }

    public List<BizModular> findAll() {
        BizModularSearchVO searchvo = new BizModularSearchVO();
        return entityMapper.searchByCriteria(searchvo);
    }

    public BizModular findByPrimaryKey(Integer keyId) {
        BizModular entity = entityMapper.selectByPrimaryKey(keyId);
        return entity;
    }

    public List<BizModular> searchByVO(BizModularSearchVO searchVO) {
        PageInfo<BizModular> pageInfo = internalfindBySearchVO(searchVO, null, null);
        return pageInfo.getList();
    }

    public PageInfo<BizModular> searchByVO(BizModularSearchVO searchVO, int page, int pageSize) {
        return internalfindBySearchVO(searchVO, page, pageSize);
    }

    protected PageInfo<BizModular> internalfindBySearchVO(BizModularSearchVO searchvo, Integer page, Integer pageSize) {
        Page<Object> pagesvo = null;
        if (page != null) {
            pagesvo = PageHelper.startPage(page, pageSize);
        }
        if (searchvo instanceof CriteriaBuilder) {
            ((CriteriaBuilder) searchvo).build();
        }
        List<BizModular> list = entityMapper.searchByCriteria(searchvo);
        PageInfo<BizModular> pageInfo = new PageInfo<BizModular>(list, pageSize);
        if (pagesvo != null) {
            pageInfo.setTotal(pagesvo.getTotal());
        }
        return pageInfo;
    }
}