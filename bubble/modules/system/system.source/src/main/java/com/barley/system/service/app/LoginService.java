package com.barley.system.service.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.barley.system.application.LoginContext;
import com.barley.system.controller.app.handler.DelegatingLoginHander;
import com.barley.system.modal.SysUser;
import com.barley.system.service.base.SysUserService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * login service
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: LoginService.java, V1.0.0 2020年12月31日 上午11:29:54 $
 */

@Service
@Transactional
@Slf4j
public class LoginService {

	/**
	 * 
	 * login
	 */
	public void login(LoginContext context) {
		Assert.notNull(context.getUserId(), "context user id is null.");
		String userId = context.getUserId();
		log.info("user login, userid=[{}]", userId);
		SysUser sysuser = servSysUser.findByPrimaryKey(userId);
		context.setSysuser(sysuser);
		handlerAggregetor.login(context);

	}

	@Autowired
	private SysUserService servSysUser;

	@Autowired
	private DelegatingLoginHander handlerAggregetor;
}
