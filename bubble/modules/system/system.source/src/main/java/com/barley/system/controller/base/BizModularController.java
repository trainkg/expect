package com.barley.system.controller.base;

import com.barley.system.modal.BizModular;
import com.barley.system.service.base.BizModularService;
import com.barley.system.service.base.searchvo.BizModularSearchVO;
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
 * @version $ID: com.barley.system.controller.base.BizModularController create date 2020-12-30 21:58:50
 */
@RestController
@RequestMapping("/bizModular")
public class BizModularController {
    @Autowired
    private BizModularService servEntity;

    @RequestMapping("/create")
    public Resonse create(@ModelAttribute BizModular record) {
        servEntity.create(record);
        return Resonse.newSucessResult("create success");
    }

    @GetMapping("/rmbykey/{key}")
    public Resonse deleteByKey(@PathVariable("key") Integer keyObj) {
        servEntity.delete(keyObj);
        return Resonse.newSucessResult("delete success");
    }

    @RequestMapping("/update")
    public Resonse maintenance(@ModelAttribute BizModular record) {
        servEntity.update(record);
        return Resonse.newSucessResult("update success");
    }

    @RequestMapping("/query")
    public Resonse query(@ModelAttribute BizModularSearchVO searchVO) {
        List<BizModular> results = servEntity.searchByVO(searchVO);
        return Resonse.newSucessResult("query success",results);
    }

    @GetMapping("/qrybykey/{key}")
    public BizModular searchByKey(@PathVariable("key") Integer keyObj) {
        return servEntity.findByPrimaryKey(keyObj);
    }
}