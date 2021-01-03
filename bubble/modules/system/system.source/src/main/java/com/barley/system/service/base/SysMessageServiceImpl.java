package com.barley.system.service.base;

import com.barley.system.mappers.SysMessageMapper;
import com.barley.system.modal.SysMessage;
import com.barley.system.service.base.searchvo.SysMessageSearchVO;
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
 * @version $ID: com.barley.system.service.base.SysMessageBaseService create date 2020-12-30 21:58:50
 */
@Service
@Transactional
public class SysMessageServiceImpl implements SysMessageService {
    @Autowired
    protected SysMessageMapper entityMapper;

    public SysMessage create(SysMessage record) {
        entityMapper.insert(record);
        return record;
    }

    public void delete(Integer keyId) {
        entityMapper.deleteByPrimaryKey(keyId);
    }

    public SysMessage update(SysMessage record) {
        entityMapper.updateByPrimaryKey(record);
        return record;
    }

    public List<SysMessage> findAll() {
        SysMessageSearchVO searchvo = new SysMessageSearchVO();
        return entityMapper.searchByCriteria(searchvo);
    }

    public SysMessage findByPrimaryKey(Integer keyId) {
        SysMessage entity = entityMapper.selectByPrimaryKey(keyId);
        return entity;
    }

    public List<SysMessage> searchByVO(SysMessageSearchVO searchVO) {
        PageInfo<SysMessage> pageInfo = internalfindBySearchVO(searchVO, null, null);
        return pageInfo.getList();
    }

    public PageInfo<SysMessage> searchByVO(SysMessageSearchVO searchVO, int page, int pageSize) {
        return internalfindBySearchVO(searchVO, page, pageSize);
    }

    protected PageInfo<SysMessage> internalfindBySearchVO(SysMessageSearchVO searchvo, Integer page, Integer pageSize) {
        Page<Object> pagesvo = null;
        if (page != null) {
            pagesvo = PageHelper.startPage(page, pageSize);
        }
        if (searchvo instanceof CriteriaBuilder) {
            ((CriteriaBuilder) searchvo).build();
        }
        List<SysMessage> list = entityMapper.searchByCriteria(searchvo);
        PageInfo<SysMessage> pageInfo = new PageInfo<SysMessage>(list, pageSize);
        if (pagesvo != null) {
            pageInfo.setTotal(pagesvo.getTotal());
        }
        return pageInfo;
    }
}