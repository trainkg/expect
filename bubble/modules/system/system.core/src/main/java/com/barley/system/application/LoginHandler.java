package com.barley.system.application;


/**
 * 
 * system application login
 * 
 * @author peculiar.1@163.com
 * @version $ID: LoginHander.java, V1.0.0 2020年12月31日 上午10:02:05 $
 */
public interface LoginHandler {
	
	
	/**
	 * 
	 * 
	 * 
	 * system login hander. 
	 * 
	 * 
	 * 
	 * @throws LoginException
	 */
	void login(LoginContext context) throws LoginException;
	
}
