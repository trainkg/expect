package com.barley.party.controller;

import com.barley.party.modal.Organization;
import com.barley.party.service.OrganizationService;
import com.barley.party.service.searchvo.OrganizationSearchVO;
import java.util.List;
import org.barley.web.Resonse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author peculiar.1@163.com
 * @version $ID: com.barley.party.controller.OrganizationController create date 2020-12-27 14:50:38
 */
@RestController
@RequestMapping("/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService servEntity;

    @RequestMapping("/create")
    public Resonse create(@ModelAttribute Organization record) {
        servEntity.create(record);
        return Resonse.newSucessResult("create success");
    }

    @GetMapping("/rmbykey/{key}")
    public Resonse deleteByKey(@PathVariable("key") String keyObj) {
        servEntity.delete(keyObj);
        return Resonse.newSucessResult("delete success");
    }

    @RequestMapping("/update")
    public Resonse maintenance(@ModelAttribute Organization record) {
        servEntity.update(record);
        return Resonse.newSucessResult("update success");
    }

    @RequestMapping("/query")
    public Resonse query(@ModelAttribute OrganizationSearchVO searchVO) {
        List<Organization> results = servEntity.searchByVO(searchVO);
        return Resonse.newSucessResult("update success",results);
    }

    @GetMapping("/qrybykey/{key}")
    public Organization searchByKey(@PathVariable("key") String keyObj) {
        return servEntity.findByPrimaryKey(keyObj);
    }
}