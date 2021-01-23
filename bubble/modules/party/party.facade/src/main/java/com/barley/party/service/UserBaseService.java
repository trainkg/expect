package com.barley.party.service;

import com.barley.party.modal.User;
import com.barley.party.service.searchvo.UserSearchVO;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * Auto generate , don't modify this file.
 * @author peculiar.1@163.com
 * @version $ID: com.barley.party.service.UserBaseService create date 2020-12-27 15:11:34
 */
public interface UserBaseService {
    User create(User record);

    void delete(String keyId);

    User update(User record);

    List<User> findAll();

    User findByPrimaryKey(String keyId);

    List<User> searchByVO(UserSearchVO searchVO);

    PageInfo<User> searchByVO(UserSearchVO searchVO, int page, int pageSize);
}