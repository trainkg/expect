package com.barley.party.services;

import java.util.List;

import com.barley.party.modal.User;
import com.barley.party.vo.UserSearchVO;
import com.github.pagehelper.PageInfo;

public interface UserService {

    /**
     * 新增用户（传入对象即可）PS:传入对象前请判断用户名、邮箱、手机的唯一性
     * @param user 用户实体对象
     * @return User 用户实体对象
     * @ 抛出BaseException异常
     * */
    public void createUser(User user) ;
    
    public User findById(String id);
    
    public List<User> findBySearchVO(UserSearchVO searchvo);
    
    public void deleteById(String id);
    
    public void updateUser(User user);
    
    public PageInfo<User> findBySearchVO(UserSearchVO searchvo, int page, int pageSize);
    
}
