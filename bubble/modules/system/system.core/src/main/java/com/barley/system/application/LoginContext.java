package com.barley.system.application;

import javax.servlet.http.HttpServletRequest;

import com.barley.system.modal.SysUser;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * Login Context
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: LoginContext.java, V1.0.0 2020年12月31日 下午12:59:14 $
 */
public class LoginContext {
	
	
	public LoginContext() {
	}

	@Getter
	@Setter
	private String userId;

	@Getter
	@Setter
	private SysUser sysuser;
	
	@Setter
	@Getter
	private HttpServletRequest request;
}
