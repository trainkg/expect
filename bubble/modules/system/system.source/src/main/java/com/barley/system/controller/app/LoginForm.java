package com.barley.system.controller.app;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 
 *
 * system login form informations
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: LoginForm.java, V1.0.0 2020年12月31日 上午10:15:33 $
 */

public class LoginForm {

	/**
	 * 
	 * login name
	 * 
	 */
	@Getter
	@Setter
	private String userName;

	/**
	 * 
	 * password
	 * 
	 */
	@Getter
	@Setter
	private String password;

	/**
	 * 
	 * login from .
	 * 
	 */
	@Getter
	@Setter
	private String channel;

	/**
	 * 
	 * login with secret token
	 * 
	 */
	@Getter
	@Setter
	private String secret;

	/**
	 * 
	 * Login type
	 */
	private LoginType type;

	/**
	 * 
	 * get login type
	 * 
	 * @return
	 */
	public LoginType getType() {

		if (StringUtils.isNotBlank(password)) {
			return LoginType.password;
		}

		if (StringUtils.isNotBlank(secret)) {
			return LoginType.token;
		}

		return type;
	}

}
