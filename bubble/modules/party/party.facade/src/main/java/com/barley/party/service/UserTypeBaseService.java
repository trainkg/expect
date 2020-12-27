package com.barley.party.service;

import com.barley.party.modal.UserType;
import com.barley.party.service.searchvo.UserTypeSearchVO;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * Auto generate , don't modify this file.
 * @author peculiar.1@163.com
 * @version $ID: com.barley.party.service.UserTypeBaseService create date 2020-12-27 15:11:34
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