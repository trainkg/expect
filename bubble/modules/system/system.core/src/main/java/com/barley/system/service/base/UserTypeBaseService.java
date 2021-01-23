package com.barley.system.service.base;

import com.barley.system.modal.UserType;
import com.barley.system.service.base.searchvo.UserTypeSearchVO;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * Auto generate , don't modify this file.
 * @author peculiar.1@163.com
 * @version $ID: com.barley.system.service.base.UserTypeBaseService create date 2021-01-23 14:30:29
 */
public interface UserTypeBaseService {
    UserType create(UserType record);

    void delete(Short keyId);

    UserType update(UserType record);

    List<UserType> findAll();

    UserType findByPrimaryKey(Short keyId);

    List<UserType> searchByVO(UserTypeSearchVO searchVO);

    PageInfo<UserType> searchByVO(UserTypeSearchVO searchVO, int page, int pageSize);
}