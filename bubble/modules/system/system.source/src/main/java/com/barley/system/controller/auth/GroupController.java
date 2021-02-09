package com.barley.system.controller.auth;

import java.util.List;

import org.barley.web.Resonse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.barley.system.modal.Group;
import com.barley.system.service.auth.GroupService;
import com.barley.system.service.auth.searchvo.GroupSearchVO;
import com.github.pagehelper.PageInfo;

/**
 * @author peculiar.1@163.com
 * @version $ID: com.barley.system.controller.auth.GroupController
 */
@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupService servEntity;

    @RequestMapping("/create")
    public Resonse create(@RequestBody Group record) {
        servEntity.create(record);
        return Resonse.newSucessResult("create success");
    }

    @GetMapping("/rmbykey/{key}")
    public Resonse deleteByKey(@PathVariable("key") Integer keyObj) {
        servEntity.delete(keyObj);
        return Resonse.newSucessResult("delete success");
    }

    @RequestMapping("/update")
    public Resonse maintenance(@RequestBody Group record) {
        servEntity.update(record);
        return Resonse.newSucessResult("update success");
    }

    @RequestMapping("/query")
    public Resonse query(@RequestBody GroupSearchVO searchVO) {
        List<Group> results = servEntity.searchByVO(searchVO);
        return Resonse.newSucessResult("query success",results);
    }

    @GetMapping("/qrybykey/{key}")
    public Group searchByKey(@PathVariable("key") Integer keyObj) {
        return servEntity.findByPrimaryKey(keyObj);
    }

    @RequestMapping("/pqry")
    public PageInfo<Group> pagingQuery(@RequestBody GroupSearchVO searchVO, @RequestParam(required = false, defaultValue = "1") int page, @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return servEntity.searchByVO(searchVO, page, pageSize);
    }
}