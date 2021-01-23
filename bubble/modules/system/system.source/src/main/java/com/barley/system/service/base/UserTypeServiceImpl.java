package com.barley.system.service.base;

import com.barley.system.mappers.UserTypeMapper;
import com.barley.system.modal.UserType;
import com.barley.system.service.base.searchvo.UserTypeSearchVO;
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
 * @version $ID: com.barley.system.service.base.UserTypeBaseService create date 2021-01-23 16:09:53
 */
@Service
@Transactional
public class UserTypeServiceImpl implements UserTypeService {
    @Autowired
    protected UserTypeMapper entityMapper;

    public UserType create(UserType record) {
        entityMapper.insert(record);
        return record;
    }

    public void delete(Short keyId) {
        entityMapper.deleteByPrimaryKey(keyId);
    }

    public UserType update(UserType record) {
        entityMapper.updateByPrimaryKey(record);
        return record;
    }

    public List<UserType> findAll() {
        UserTypeSearchVO searchvo = new UserTypeSearchVO();
        return entityMapper.searchByCriteria(searchvo);
    }

    public UserType findByPrimaryKey(Short keyId) {
        UserType entity = entityMapper.selectByPrimaryKey(keyId);
        return entity;
    }

    public List<UserType> searchByVO(UserTypeSearchVO searchVO) {
        PageInfo<UserType> pageInfo = internalfindBySearchVO(searchVO, null, null);
        return pageInfo.getList();
    }

    public PageInfo<UserType> searchByVO(UserTypeSearchVO searchVO, int page, int pageSize) {
        return internalfindBySearchVO(searchVO, page, pageSize);
    }

    protected PageInfo<UserType> internalfindBySearchVO(UserTypeSearchVO searchvo, Integer page, Integer pageSize) {
        Page<Object> pagesvo = null;
        if (page != null) {
            pagesvo = PageHelper.startPage(page, pageSize);
        }
        if (searchvo instanceof CriteriaBuilder) {
            ((CriteriaBuilder) searchvo).build();
        }
        List<UserType> list = entityMapper.searchByCriteria(searchvo);
        PageInfo<UserType> pageInfo = new PageInfo<UserType>(list, pageSize);
        if (pagesvo != null) {
            pageInfo.setTotal(pagesvo.getTotal());
        }
        return pageInfo;
    }
}