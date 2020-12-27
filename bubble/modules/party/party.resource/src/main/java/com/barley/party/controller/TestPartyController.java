package com.barley.party.controller;

import com.barley.party.modal.TestParty;
import com.barley.party.modal.TestPartyKey;
import com.barley.party.service.TestPartyService;
import com.barley.party.service.searchvo.TestPartySearchVO;
import java.util.List;
import org.barley.web.Resonse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author peculiar.1@163.com
 * @version $ID: com.barley.party.controller.TestPartyController create date 2020-12-27 14:50:38
 */
@RestController
@RequestMapping("/testParty")
public class TestPartyController {
    @Autowired
    private TestPartyService servEntity;

    @RequestMapping("/create")
    public Resonse create(@ModelAttribute TestParty record) {
        servEntity.create(record);
        return Resonse.newSucessResult("create success");
    }

    @RequestMapping("/rmbykey")
    public Resonse deleteByKey(@ModelAttribute TestPartyKey keyObj) {
        servEntity.delete(keyObj);
        return Resonse.newSucessResult("delete success");
    }

    @RequestMapping("/update")
    public Resonse maintenance(@ModelAttribute TestParty record) {
        servEntity.update(record);
        return Resonse.newSucessResult("update success");
    }

    @RequestMapping("/query")
    public Resonse query(@ModelAttribute TestPartySearchVO searchVO) {
        List<TestParty> results = servEntity.searchByVO(searchVO);
        return Resonse.newSucessResult("update success",results);
    }

    @RequestMapping("/qrybykey")
    public TestParty searchByKey(@ModelAttribute TestPartyKey keyObj) {
        return servEntity.findByPrimaryKey(keyObj);
    }
}