package com.barley.system.controller.auth;

import com.barley.system.modal.Role;
import com.barley.system.service.auth.RoleService;
import com.barley.system.service.auth.searchvo.RoleSearchVO;
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.barley.web.Resonse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author peculiar.1@163.com
 * @version $ID: com.barley.system.controller.auth.RoleController
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService servEntity;

    @RequestMapping("/create")
    public Resonse create(@RequestBody Role record) {
        servEntity.create(record);
        return Resonse.newSucessResult("create success");
    }

    @GetMapping("/rmbykey/{key}")
    public Resonse deleteByKey(@PathVariable("key") Integer keyObj) {
        servEntity.delete(keyObj);
        return Resonse.newSucessResult("delete success");
    }

    @RequestMapping("/update")
    public Resonse maintenance(@RequestBody Role record) {
        servEntity.update(record);
        return Resonse.newSucessResult("update success");
    }

    @RequestMapping("/query")
    public Resonse query(@RequestBody RoleSearchVO searchVO) {
        List<Role> results = servEntity.searchByVO(searchVO);
        return Resonse.newSucessResult("query success",results);
    }

    @GetMapping("/qrybykey/{key}")
    public Role searchByKey(@PathVariable("key") Integer keyObj) {
        return servEntity.findByPrimaryKey(keyObj);
    }

    @RequestMapping("/pqry")
    public PageInfo<Role> pagingQuery(@RequestBody RoleSearchVO searchVO, @RequestParam(required = false, defaultValue = "1") int page, @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return servEntity.searchByVO(searchVO, page, pageSize);
    }
}