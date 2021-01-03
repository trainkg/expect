package com.barley.system.service.base;

import com.barley.system.mappers.LoginChannelMapper;
import com.barley.system.modal.LoginChannel;
import com.barley.system.service.base.searchvo.LoginChannelSearchVO;
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
 * @version $ID: com.barley.system.service.base.LoginChannelBaseService create date 2020-12-31 10:19:18
 */
@Service
@Transactional
public class LoginChannelServiceImpl implements LoginChannelService {
    @Autowired
    protected LoginChannelMapper entityMapper;

    public LoginChannel create(LoginChannel record) {
        entityMapper.insert(record);
        return record;
    }

    public void delete(Integer keyId) {
        entityMapper.deleteByPrimaryKey(keyId);
    }

    public LoginChannel update(LoginChannel record) {
        entityMapper.updateByPrimaryKey(record);
        return record;
    }

    public List<LoginChannel> findAll() {
        LoginChannelSearchVO searchvo = new LoginChannelSearchVO();
        return entityMapper.searchByCriteria(searchvo);
    }

    public LoginChannel findByPrimaryKey(Integer keyId) {
        LoginChannel entity = entityMapper.selectByPrimaryKey(keyId);
        return entity;
    }

    public List<LoginChannel> searchByVO(LoginChannelSearchVO searchVO) {
        PageInfo<LoginChannel> pageInfo = internalfindBySearchVO(searchVO, null, null);
        return pageInfo.getList();
    }

    public PageInfo<LoginChannel> searchByVO(LoginChannelSearchVO searchVO, int page, int pageSize) {
        return internalfindBySearchVO(searchVO, page, pageSize);
    }

    protected PageInfo<LoginChannel> internalfindBySearchVO(LoginChannelSearchVO searchvo, Integer page, Integer pageSize) {
        Page<Object> pagesvo = null;
        if (page != null) {
            pagesvo = PageHelper.startPage(page, pageSize);
        }
        if (searchvo instanceof CriteriaBuilder) {
            ((CriteriaBuilder) searchvo).build();
        }
        List<LoginChannel> list = entityMapper.searchByCriteria(searchvo);
        PageInfo<LoginChannel> pageInfo = new PageInfo<LoginChannel>(list, pageSize);
        if (pagesvo != null) {
            pageInfo.setTotal(pagesvo.getTotal());
        }
        return pageInfo;
    }
}