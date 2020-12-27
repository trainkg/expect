package com.barley.party.controller;

import com.barley.party.modal.Department;
import com.barley.party.service.DepartmentService;
import com.barley.party.service.searchvo.DepartmentSearchVO;
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
 * @version $ID: com.barley.party.controller.DepartmentController create date 2020-12-27 14:50:38
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService servEntity;

    @RequestMapping("/create")
    public Resonse create(@ModelAttribute Department record) {
        servEntity.create(record);
        return Resonse.newSucessResult("create success");
    }

    @GetMapping("/rmbykey/{key}")
    public Resonse deleteByKey(@PathVariable("key") Long keyObj) {
        servEntity.delete(keyObj);
        return Resonse.newSucessResult("delete success");
    }

    @RequestMapping("/update")
    public Resonse maintenance(@ModelAttribute Department record) {
        servEntity.update(record);
        return Resonse.newSucessResult("update success");
    }

    @RequestMapping("/query")
    public Resonse query(@ModelAttribute DepartmentSearchVO searchVO) {
        List<Department> results = servEntity.searchByVO(searchVO);
        return Resonse.newSucessResult("update success",results);
    }

    @GetMapping("/qrybykey/{key}")
    public Department searchByKey(@PathVariable("key") Long keyObj) {
        return servEntity.findByPrimaryKey(keyObj);
    }
}