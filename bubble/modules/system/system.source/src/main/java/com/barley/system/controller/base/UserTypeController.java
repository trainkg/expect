package com.barley.system.controller.base;

import com.barley.system.modal.UserType;
import com.barley.system.service.base.UserTypeService;
import com.barley.system.service.base.searchvo.UserTypeSearchVO;
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
 * @version $ID: com.barley.system.controller.base.UserTypeController create date 2021-01-23 16:09:53
 */
@RestController
@RequestMapping("/userType")
public class UserTypeController {
    @Autowired
    private UserTypeService servEntity;

    @RequestMapping("/create")
    public Resonse create(@ModelAttribute UserType record) {
        servEntity.create(record);
        return Resonse.newSucessResult("create success");
    }

    @GetMapping("/rmbykey/{key}")
    public Resonse deleteByKey(@PathVariable("key") Short keyObj) {
        servEntity.delete(keyObj);
        return Resonse.newSucessResult("delete success");
    }

    @RequestMapping("/update")
    public Resonse maintenance(@ModelAttribute UserType record) {
        servEntity.update(record);
        return Resonse.newSucessResult("update success");
    }

    @RequestMapping("/query")
    public Resonse query(@ModelAttribute UserTypeSearchVO searchVO) {
        List<UserType> results = servEntity.searchByVO(searchVO);
        return Resonse.newSucessResult("query success",results);
    }

    @GetMapping("/qrybykey/{key}")
    public UserType searchByKey(@PathVariable("key") Short keyObj) {
        return servEntity.findByPrimaryKey(keyObj);
    }
}