package com.barley.system.controller.base;

import com.barley.system.modal.SysMessage;
import com.barley.system.service.base.SysMessageService;
import com.barley.system.service.base.searchvo.SysMessageSearchVO;
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
 * @version $ID: com.barley.system.controller.base.SysMessageController create date 2020-12-30 21:58:50
 */
@RestController
@RequestMapping("/sysMessage")
public class SysMessageController {
    @Autowired
    private SysMessageService servEntity;

    @RequestMapping("/create")
    public Resonse create(@ModelAttribute SysMessage record) {
        servEntity.create(record);
        return Resonse.newSucessResult("create success");
    }

    @GetMapping("/rmbykey/{key}")
    public Resonse deleteByKey(@PathVariable("key") Integer keyObj) {
        servEntity.delete(keyObj);
        return Resonse.newSucessResult("delete success");
    }

    @RequestMapping("/update")
    public Resonse maintenance(@ModelAttribute SysMessage record) {
        servEntity.update(record);
        return Resonse.newSucessResult("update success");
    }

    @RequestMapping("/query")
    public Resonse query(@ModelAttribute SysMessageSearchVO searchVO) {
        List<SysMessage> results = servEntity.searchByVO(searchVO);
        return Resonse.newSucessResult("query success",results);
    }

    @GetMapping("/qrybykey/{key}")
    public SysMessage searchByKey(@PathVariable("key") Integer keyObj) {
        return servEntity.findByPrimaryKey(keyObj);
    }
}