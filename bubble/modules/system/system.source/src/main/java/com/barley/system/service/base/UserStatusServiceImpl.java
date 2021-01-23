package com.barley.system.service.base;

import com.barley.system.mappers.UserStatusMapper;
import com.barley.system.modal.UserStatus;
import com.barley.system.service.base.searchvo.UserStatusSearchVO;
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
 * @version $ID: com.barley.system.service.base.UserStatusBaseService create date 2021-01-23 14:53:28
 */
@Service
@Transactional
public class UserStatusServiceImpl implements UserStatusService {
    @Autowired
    protected UserStatusMapper entityMapper;

    public UserStatus create(UserStatus record) {
        entityMapper.insert(record);
        return record;
    }

    public void delete(Short keyId) {
        entityMapper.deleteByPrimaryKey(keyId);
    }

    public UserStatus update(UserStatus record) {
        entityMapper.updateByPrimaryKey(record);
        return record;
    }

    public List<UserStatus> findAll() {
        UserStatusSearchVO searchvo = new UserStatusSearchVO();
        return entityMapper.searchByCriteria(searchvo);
    }

    public UserStatus findByPrimaryKey(Short keyId) {
        UserStatus entity = entityMapper.selectByPrimaryKey(keyId);
        return entity;
    }

    public List<UserStatus> searchByVO(UserStatusSearchVO searchVO) {
        PageInfo<UserStatus> pageInfo = internalfindBySearchVO(searchVO, null, null);
        return pageInfo.getList();
    }

    public PageInfo<UserStatus> searchByVO(UserStatusSearchVO searchVO, int page, int pageSize) {
        return internalfindBySearchVO(searchVO, page, pageSize);
    }

    protected PageInfo<UserStatus> internalfindBySearchVO(UserStatusSearchVO searchvo, Integer page, Integer pageSize) {
        Page<Object> pagesvo = null;
        if (page != null) {
            pagesvo = PageHelper.startPage(page, pageSize);
        }
        if (searchvo instanceof CriteriaBuilder) {
            ((CriteriaBuilder) searchvo).build();
        }
        List<UserStatus> list = entityMapper.searchByCriteria(searchvo);
        PageInfo<UserStatus> pageInfo = new PageInfo<UserStatus>(list, pageSize);
        if (pagesvo != null) {
            pageInfo.setTotal(pagesvo.getTotal());
        }
        return pageInfo;
    }
}