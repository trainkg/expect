package com.barley.config.controller;

import com.barley.config.modal.Form;
import com.barley.config.service.FormService;
import com.barley.config.service.searchvo.FormSearchVO;
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
 * @version $ID: com.barley.config.controller.FormController create date 2020-12-31 13:35:23
 */
@RestController
@RequestMapping("/form")
public class FormController {
    @Autowired
    private FormService servEntity;

    @RequestMapping("/create")
    public Resonse create(@ModelAttribute Form record) {
        servEntity.create(record);
        return Resonse.newSucessResult("create success");
    }

    @GetMapping("/rmbykey/{key}")
    public Resonse deleteByKey(@PathVariable("key") String keyObj) {
        servEntity.delete(keyObj);
        return Resonse.newSucessResult("delete success");
    }

    @RequestMapping("/update")
    public Resonse maintenance(@ModelAttribute Form record) {
        servEntity.update(record);
        return Resonse.newSucessResult("update success");
    }

    @RequestMapping("/query")
    public Resonse query(@ModelAttribute FormSearchVO searchVO) {
        List<Form> results = servEntity.searchByVO(searchVO);
        return Resonse.newSucessResult("query success",results);
    }

    @GetMapping("/qrybykey/{key}")
    public Form searchByKey(@PathVariable("key") String keyObj) {
        return servEntity.findByPrimaryKey(keyObj);
    }
}