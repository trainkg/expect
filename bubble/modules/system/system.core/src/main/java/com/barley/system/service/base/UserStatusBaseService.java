package com.barley.system.service.base;

import com.barley.system.modal.UserStatus;
import com.barley.system.service.base.searchvo.UserStatusSearchVO;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * Auto generate , don't modify this file.
 * @author peculiar.1@163.com
 * @version $ID: com.barley.system.service.base.UserStatusBaseService create date 2021-01-23 16:09:53
 */
public interface UserStatusBaseService {
    UserStatus create(UserStatus record);

    void delete(Short keyId);

    UserStatus update(UserStatus record);

    List<UserStatus> findAll();

    UserStatus findByPrimaryKey(Short keyId);

    List<UserStatus> searchByVO(UserStatusSearchVO searchVO);

    PageInfo<UserStatus> searchByVO(UserStatusSearchVO searchVO, int page, int pageSize);
}