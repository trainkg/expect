package com.barley.system.service.auth;

import com.barley.system.mappers.PermissionMapper;
import com.barley.system.modal.Permission;
import com.barley.system.service.auth.searchvo.PermissionSearchVO;
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
 * @version $ID: com.barley.system.service.auth.PermissionBaseService
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    protected PermissionMapper entityMapper;

    public Permission create(Permission record) {
        entityMapper.insert(record);
        return record;
    }

    public void delete(Integer keyId) {
        entityMapper.deleteByPrimaryKey(keyId);
    }

    public Permission update(Permission record) {
        entityMapper.updateByPrimaryKey(record);
        return record;
    }

    public List<Permission> findAll() {
        PermissionSearchVO searchvo = new PermissionSearchVO();
        return entityMapper.searchByCriteria(searchvo);
    }

    public Permission findByPrimaryKey(Integer keyId) {
        Permission entity = entityMapper.selectByPrimaryKey(keyId);
        return entity;
    }

    public List<Permission> searchByVO(PermissionSearchVO searchVO) {
        PageInfo<Permission> pageInfo = internalfindBySearchVO(searchVO, null, null);
        return pageInfo.getList();
    }

    public PageInfo<Permission> searchByVO(PermissionSearchVO searchVO, int page, int pageSize) {
        return internalfindBySearchVO(searchVO, page, pageSize);
    }

    protected PageInfo<Permission> internalfindBySearchVO(PermissionSearchVO searchvo, Integer page, Integer pageSize) {
        Page<Object> pagesvo = null;
        if (page != null) {
            pagesvo = PageHelper.startPage(page, pageSize);
        }
        if (searchvo instanceof CriteriaBuilder) {
            ((CriteriaBuilder) searchvo).build();
        }
        List<Permission> list = entityMapper.searchByCriteria(searchvo);
        PageInfo<Permission> pageInfo = null;
        if(pageSize != null) {
            pageInfo = new PageInfo<Permission>(list, pageSize);
            if (pagesvo != null) {
                pageInfo.setTotal(pagesvo.getTotal());
            }
        }else {
            pageInfo = new PageInfo<Permission>(list);
        }
        return pageInfo;
    }
}