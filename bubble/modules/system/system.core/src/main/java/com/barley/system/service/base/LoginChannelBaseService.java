package com.barley.system.service.base;

import com.barley.system.modal.LoginChannel;
import com.barley.system.service.base.searchvo.LoginChannelSearchVO;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * Auto generate , don't modify this file.
 * @author peculiar.1@163.com
 * @version $ID: com.barley.system.service.base.LoginChannelBaseService
 */
public interface LoginChannelBaseService {
    LoginChannel create(LoginChannel record);

    void delete(Integer keyId);

    LoginChannel update(LoginChannel record);

    List<LoginChannel> findAll();

    LoginChannel findByPrimaryKey(Integer keyId);

    List<LoginChannel> searchByVO(LoginChannelSearchVO searchVO);

    PageInfo<LoginChannel> searchByVO(LoginChannelSearchVO searchVO, int page, int pageSize);
}