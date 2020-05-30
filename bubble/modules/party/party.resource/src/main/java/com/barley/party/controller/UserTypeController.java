package com.barley.party.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.barley.party.modal.UserType;
import com.barley.party.services.UserTypeService;

/**
 * User控制器
 * 
 * @author peculiar.1@163.com
 * @version $ID: UserController.java, V1.0.0 2020年5月26日 下午9:55:00 $
 */
@RestController
@RequestMapping("/usertype")
public class UserTypeController {

    /**
     * 
     * @param userId
     * @return
     */
    @ResponseBody
    @GetMapping("/retrive/{typeId}")
    public UserType searchUserType(@PathVariable short typeId) {
        return seuserType.findById(typeId);
    }

    /**
     * 
     * @param searchvo
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public List<UserType> typeList(@RequestParam(required = false, name = "all", defaultValue = "1") short all) {
        if(all == 1) {
            return seuserType.findAll();
        }else {
            return seuserType.findAllActive();    
        }
    }

    /**
     * 
     * @param userId
     */
    @PostMapping("/remove")
    @ResponseBody
    public void inactiveUserType(@RequestParam short userTypeId) {
        seuserType.deleteById(userTypeId);
    }

    /**
     * 信息维护
     * 
     * @param userId
     */
    @PostMapping("/inactive")
    @ResponseBody
    public void maintanenceType(@ModelAttribute UserType type) {
        seuserType.updateUserType(type);
    }

    @Autowired
    private UserTypeService seuserType;
}
