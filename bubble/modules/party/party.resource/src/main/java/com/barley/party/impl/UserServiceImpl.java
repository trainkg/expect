package com.barley.party.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barley.party.mappers.UserMapper;
import com.barley.party.modal.User;
import com.barley.party.services.UserService;
import com.barley.party.vo.UserSearchVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


/**
 * 
 * @author peculiar.1@163.com
 * @version $ID: UserServiceImpl.java, V1.0.0 2020年5月21日 下午10:33:45 $
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Override
    public void createUser(User user) {
        daoUser.insert(user);
    }

    @Override
    public User findById(String id) {
        return daoUser.selectByPrimaryKey(id);
    }

    @Override
    public List<User> findBySearchVO(UserSearchVO searchvo) {
        PageInfo<User> pageInfo = internalfindBySearchVO(searchvo, null, null);
        return pageInfo.getList();
    }

    @Override
    public PageInfo<User> findBySearchVO(UserSearchVO searchvo, int page, int pageSize) {
        return internalfindBySearchVO(searchvo, page, pageSize);
    }

    /**
     * 
     * @param searchvo
     * @param page page & page size 需要同时提供
     * @param pageSize
     * @return
     */
    protected PageInfo<User> internalfindBySearchVO(UserSearchVO searchvo, Integer page, Integer pageSize) {
        searchvo.pushToCriteria();
        Page<Object> pagesvo = null;
        if (page != null) {
            pagesvo = PageHelper.startPage(page, pageSize);
        }
        List<User> list = daoUser.selectByExample(searchvo);
        PageInfo<User> pageInfo = new PageInfo<User>(list, pageSize);
        if (pagesvo != null) {
            pageInfo.setTotal(pagesvo.getTotal());
        }
        return pageInfo;
    }

    @Override
    public void deleteById(String id) {
        daoUser.deleteByPrimaryKey(id);
    }

    @Override
    public void updateUser(User user) {
        daoUser.updateByPrimaryKeySelective(user);
    }

    @Autowired
    private UserMapper daoUser;
}
