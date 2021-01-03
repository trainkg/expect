package com.barley.config.controller;

import com.barley.config.modal.CodeTable;
import com.barley.config.service.CodeTableService;
import com.barley.config.service.searchvo.CodeTableSearchVO;
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
 * @version $ID: com.barley.config.controller.CodeTableController create date 2020-12-31 13:35:23
 */
@RestController
@RequestMapping("/codeTable")
public class CodeTableController {
    @Autowired
    private CodeTableService servEntity;

    @RequestMapping("/create")
    public Resonse create(@ModelAttribute CodeTable record) {
        servEntity.create(record);
        return Resonse.newSucessResult("create success");
    }

    @GetMapping("/rmbykey/{key}")
    public Resonse deleteByKey(@PathVariable("key") Long keyObj) {
        servEntity.delete(keyObj);
        return Resonse.newSucessResult("delete success");
    }

    @RequestMapping("/update")
    public Resonse maintenance(@ModelAttribute CodeTable record) {
        servEntity.update(record);
        return Resonse.newSucessResult("update success");
    }

    @RequestMapping("/query")
    public Resonse query(@ModelAttribute CodeTableSearchVO searchVO) {
        List<CodeTable> results = servEntity.searchByVO(searchVO);
        return Resonse.newSucessResult("query success",results);
    }

    @GetMapping("/qrybykey/{key}")
    public CodeTable searchByKey(@PathVariable("key") Long keyObj) {
        return servEntity.findByPrimaryKey(keyObj);
    }
}