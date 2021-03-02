package com.barley.system.controller.base;

import com.barley.system.modal.UserType;
import com.barley.system.service.base.UserTypeService;
import com.barley.system.service.base.searchvo.UserTypeSearchVO;
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
 * @version $ID: com.barley.system.controller.base.UserTypeController
 */
@RestController
@RequestMapping("/userType")
public class UserTypeController {
    @Autowired
    private UserTypeService servEntity;

    @RequestMapping("/create")
    public Resonse create(@RequestBody UserType record) {
        servEntity.create(record);
        return Resonse.newSucessResult("create success");
    }

    @GetMapping("/rmbykey/{key}")
    public Resonse deleteByKey(@PathVariable("key") Short keyObj) {
        servEntity.delete(keyObj);
        return Resonse.newSucessResult("delete success");
    }

    @RequestMapping("/update")
    public Resonse maintenance(@RequestBody UserType record) {
        servEntity.update(record);
        return Resonse.newSucessResult("update success");
    }

    @RequestMapping("/query")
    public Resonse query(@RequestBody UserTypeSearchVO searchVO) {
        List<UserType> results = servEntity.searchByVO(searchVO);
        return Resonse.newSucessResult("query success",results);
    }

    @GetMapping("/qrybykey/{key}")
    public UserType searchByKey(@PathVariable("key") Short keyObj) {
        return servEntity.findByPrimaryKey(keyObj);
    }

    @RequestMapping("/pqry")
    public PageInfo<UserType> pagingQuery(@RequestBody UserTypeSearchVO searchVO, @RequestParam(required = false, defaultValue = "1") int page, @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return servEntity.searchByVO(searchVO, page, pageSize);
    }
}