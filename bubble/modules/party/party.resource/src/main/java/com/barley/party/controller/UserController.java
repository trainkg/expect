package com.barley.party.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.barley.party.form.UserSeasrchForm;
import com.barley.party.modal.User;
import com.barley.party.services.UserService;
import com.github.pagehelper.PageInfo;

/**
 * User控制器
 * 
 * @author peculiar.1@163.com
 * @version $ID: UserController.java, V1.0.0 2020年5月26日 下午9:55:00 $
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 获取user信息
     * 
     * @param userId
     * @return
     */
    @ResponseBody
    @GetMapping("/retrive/{userId}")
    public User searchUser(@PathVariable String userId) {
        return seuser.findById(userId);
    }


    /**
     * loading user table information
     * 
     * @param searchvo
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public PageInfo<User> searchUserPageInfo(@ModelAttribute UserSeasrchForm searchForm,
                    @RequestParam(required = false, defaultValue = "1") Integer page,
                    @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return seuser.findBySearchVO(searchForm, page, pageSize);
    }

    /**
     * remove
     * 
     * @param userId
     */
    @PostMapping("/remove")
    @ResponseBody
    public void removeUser(@RequestParam String userId) {
        seuser.deleteById(userId);
    }

    @Autowired
    private UserService seuser;
}
