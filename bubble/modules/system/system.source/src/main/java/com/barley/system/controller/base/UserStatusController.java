package com.barley.system.controller.base;

import com.barley.system.modal.UserStatus;
import com.barley.system.service.base.UserStatusService;
import com.barley.system.service.base.searchvo.UserStatusSearchVO;
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
 * @version $ID: com.barley.system.controller.base.UserStatusController create date 2021-01-23 14:30:29
 */
@RestController
@RequestMapping("/userStatus")
public class UserStatusController {
    @Autowired
    private UserStatusService servEntity;

    @RequestMapping("/create")
    public Resonse create(@ModelAttribute UserStatus record) {
        servEntity.create(record);
        return Resonse.newSucessResult("create success");
    }

    @GetMapping("/rmbykey/{key}")
    public Resonse deleteByKey(@PathVariable("key") Short keyObj) {
        servEntity.delete(keyObj);
        return Resonse.newSucessResult("delete success");
    }

    @RequestMapping("/update")
    public Resonse maintenance(@ModelAttribute UserStatus record) {
        servEntity.update(record);
        return Resonse.newSucessResult("update success");
    }

    @RequestMapping("/query")
    public Resonse query(@ModelAttribute UserStatusSearchVO searchVO) {
        List<UserStatus> results = servEntity.searchByVO(searchVO);
        return Resonse.newSucessResult("query success",results);
    }

    @GetMapping("/qrybykey/{key}")
    public UserStatus searchByKey(@PathVariable("key") Short keyObj) {
        return servEntity.findByPrimaryKey(keyObj);
    }
}