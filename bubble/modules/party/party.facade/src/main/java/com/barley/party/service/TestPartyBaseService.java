package com.barley.party.service;

import com.barley.party.modal.TestParty;
import com.barley.party.modal.TestPartyKey;
import com.barley.party.service.searchvo.TestPartySearchVO;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * Auto generate , don't modify this file.
 * @author peculiar.1@163.com
 * @version $ID: com.barley.party.service.TestPartyBaseService create date 2020-12-27 15:11:34
 */
public interface TestPartyBaseService {
    TestParty create(TestParty record);

    void delete(TestPartyKey keyId);

    TestParty update(TestParty record);

    List<TestParty> findAll();

    TestParty findByPrimaryKey(TestPartyKey keyId);

    List<TestParty> searchByVO(TestPartySearchVO searchVO);

    PageInfo<TestParty> searchByVO(TestPartySearchVO searchVO, int page, int pageSize);
}