package com.barley.system.controller.base;

import com.barley.system.modal.SysUser;
import com.barley.system.service.base.SysUserService;
import com.barley.system.service.base.searchvo.SysUserSearchVO;
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
 * @version $ID: com.barley.system.controller.base.SysUserController create date 2020-12-31 12:21:20
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {
    @Autowired
    private SysUserService servEntity;

    @RequestMapping("/create")
    public Resonse create(@ModelAttribute SysUser record) {
        servEntity.create(record);
        return Resonse.newSucessResult("create success");
    }

    @GetMapping("/rmbykey/{key}")
    public Resonse deleteByKey(@PathVariable("key") String keyObj) {
        servEntity.delete(keyObj);
        return Resonse.newSucessResult("delete success");
    }

    @RequestMapping("/update")
    public Resonse maintenance(@ModelAttribute SysUser record) {
        servEntity.update(record);
        return Resonse.newSucessResult("update success");
    }

    @RequestMapping("/query")
    public Resonse query(@ModelAttribute SysUserSearchVO searchVO) {
        List<SysUser> results = servEntity.searchByVO(searchVO);
        return Resonse.newSucessResult("query success",results);
    }

    @GetMapping("/qrybykey/{key}")
    public SysUser searchByKey(@PathVariable("key") String keyObj) {
        return servEntity.findByPrimaryKey(keyObj);
    }
}