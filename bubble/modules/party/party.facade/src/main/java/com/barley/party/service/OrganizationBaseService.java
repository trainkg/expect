package com.barley.party.service;

import com.barley.party.modal.Organization;
import com.barley.party.service.searchvo.OrganizationSearchVO;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * Auto generate , don't modify this file.
 * @author peculiar.1@163.com
 * @version $ID: com.barley.party.service.OrganizationBaseService create date 2020-12-27 15:11:34
 */
public interface OrganizationBaseService {
    Organization create(Organization record);

    void delete(String keyId);

    Organization update(Organization record);

    List<Organization> findAll();

    Organization findByPrimaryKey(String keyId);

    List<Organization> searchByVO(OrganizationSearchVO searchVO);

    PageInfo<Organization> searchByVO(OrganizationSearchVO searchVO, int page, int pageSize);
}