package com.barley.config.controller;

import com.barley.config.modal.FeildType;
import com.barley.config.service.FeildTypeService;
import com.barley.config.service.searchvo.FeildTypeSearchVO;
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
 * @version $ID: com.barley.config.controller.FeildTypeController create date 2020-12-31 13:35:23
 */
@RestController
@RequestMapping("/feildType")
public class FeildTypeController {
    @Autowired
    private FeildTypeService servEntity;

    @RequestMapping("/create")
    public Resonse create(@ModelAttribute FeildType record) {
        servEntity.create(record);
        return Resonse.newSucessResult("create success");
    }

    @GetMapping("/rmbykey/{key}")
    public Resonse deleteByKey(@PathVariable("key") Short keyObj) {
        servEntity.delete(keyObj);
        return Resonse.newSucessResult("delete success");
    }

    @RequestMapping("/update")
    public Resonse maintenance(@ModelAttribute FeildType record) {
        servEntity.update(record);
        return Resonse.newSucessResult("update success");
    }

    @RequestMapping("/query")
    public Resonse query(@ModelAttribute FeildTypeSearchVO searchVO) {
        List<FeildType> results = servEntity.searchByVO(searchVO);
        return Resonse.newSucessResult("query success",results);
    }

    @GetMapping("/qrybykey/{key}")
    public FeildType searchByKey(@PathVariable("key") Short keyObj) {
        return servEntity.findByPrimaryKey(keyObj);
    }
}