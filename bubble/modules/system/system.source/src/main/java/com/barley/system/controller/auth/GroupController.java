package com.barley.system.controller.auth;

import com.barley.system.modal.Group;
import com.barley.system.service.auth.GroupService;
import com.barley.system.service.auth.searchvo.GroupSearchVO;
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
 * @version $ID: com.barley.system.controller.auth.GroupController create date 2021-01-03 11:09:28
 */
@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupService servEntity;

    @RequestMapping("/create")
    public Resonse create(@ModelAttribute Group record) {
        servEntity.create(record);
        return Resonse.newSucessResult("create success");
    }

    @GetMapping("/rmbykey/{key}")
    public Resonse deleteByKey(@PathVariable("key") Integer keyObj) {
        servEntity.delete(keyObj);
        return Resonse.newSucessResult("delete success");
    }

    @RequestMapping("/update")
    public Resonse maintenance(@ModelAttribute Group record) {
        servEntity.update(record);
        return Resonse.newSucessResult("update success");
    }

    @RequestMapping("/query")
    public Resonse query(@ModelAttribute GroupSearchVO searchVO) {
        List<Group> results = servEntity.searchByVO(searchVO);
        return Resonse.newSucessResult("query success",results);
    }

    @GetMapping("/qrybykey/{key}")
    public Group searchByKey(@PathVariable("key") Integer keyObj) {
        return servEntity.findByPrimaryKey(keyObj);
    }
}