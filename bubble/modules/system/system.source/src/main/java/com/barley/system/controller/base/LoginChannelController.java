package com.barley.system.controller.base;

import com.barley.system.modal.LoginChannel;
import com.barley.system.service.base.LoginChannelService;
import com.barley.system.service.base.searchvo.LoginChannelSearchVO;
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
 * @version $ID: com.barley.system.controller.base.LoginChannelController create date 2020-12-31 10:19:18
 */
@RestController
@RequestMapping("/loginChannel")
public class LoginChannelController {
    @Autowired
    private LoginChannelService servEntity;

    @RequestMapping("/create")
    public Resonse create(@ModelAttribute LoginChannel record) {
        servEntity.create(record);
        return Resonse.newSucessResult("create success");
    }

    @GetMapping("/rmbykey/{key}")
    public Resonse deleteByKey(@PathVariable("key") Integer keyObj) {
        servEntity.delete(keyObj);
        return Resonse.newSucessResult("delete success");
    }

    @RequestMapping("/update")
    public Resonse maintenance(@ModelAttribute LoginChannel record) {
        servEntity.update(record);
        return Resonse.newSucessResult("update success");
    }

    @RequestMapping("/query")
    public Resonse query(@ModelAttribute LoginChannelSearchVO searchVO) {
        List<LoginChannel> results = servEntity.searchByVO(searchVO);
        return Resonse.newSucessResult("query success",results);
    }

    @GetMapping("/qrybykey/{key}")
    public LoginChannel searchByKey(@PathVariable("key") Integer keyObj) {
        return servEntity.findByPrimaryKey(keyObj);
    }
}