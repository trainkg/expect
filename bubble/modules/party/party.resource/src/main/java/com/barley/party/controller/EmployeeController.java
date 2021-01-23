package com.barley.party.controller;

import com.barley.party.modal.Employee;
import com.barley.party.service.EmployeeService;
import com.barley.party.service.searchvo.EmployeeSearchVO;
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
 * @version $ID: com.barley.party.controller.EmployeeController create date 2020-12-27 14:50:38
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService servEntity;

    @RequestMapping("/create")
    public Resonse create(@ModelAttribute Employee record) {
        servEntity.create(record);
        return Resonse.newSucessResult("create success");
    }

    @GetMapping("/rmbykey/{key}")
    public Resonse deleteByKey(@PathVariable("key") Long keyObj) {
        servEntity.delete(keyObj);
        return Resonse.newSucessResult("delete success");
    }

    @RequestMapping("/update")
    public Resonse maintenance(@ModelAttribute Employee record) {
        servEntity.update(record);
        return Resonse.newSucessResult("update success");
    }

    @RequestMapping("/query")
    public Resonse query(@ModelAttribute EmployeeSearchVO searchVO) {
        List<Employee> results = servEntity.searchByVO(searchVO);
        return Resonse.newSucessResult("update success",results);
    }

    @GetMapping("/qrybykey/{key}")
    public Employee searchByKey(@PathVariable("key") Long keyObj) {
        return servEntity.findByPrimaryKey(keyObj);
    }
}