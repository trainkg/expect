package com.barley.config.service;

import com.barley.config.modal.FeildType;
import com.barley.config.service.searchvo.FeildTypeSearchVO;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * Auto generate , don't modify this file.
 * @author peculiar.1@163.com
 * @version $ID: com.barley.config.service.FeildTypeBaseService create date 2020-12-31 13:35:23
 */
public interface FeildTypeBaseService {
    FeildType create(FeildType record);

    void delete(Short keyId);

    FeildType update(FeildType record);

    List<FeildType> findAll();

    FeildType findByPrimaryKey(Short keyId);

    List<FeildType> searchByVO(FeildTypeSearchVO searchVO);

    PageInfo<FeildType> searchByVO(FeildTypeSearchVO searchVO, int page, int pageSize);
}