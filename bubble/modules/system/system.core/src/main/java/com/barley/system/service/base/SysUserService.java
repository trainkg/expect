package com.barley.system.service.base;

import com.barley.system.modal.SysUser;

/**
 * @author peculiar.1@163.com
 * @version $ID: com.barley.system.service.base.SysUserBaseService create date 2020-12-31 12:21:20
 */
public interface SysUserService extends SysUserBaseService {
	
	
	/**
	 * 
	 * 	校验用户输入的密码信息是否匹配
	 * 
	 * @param userId
	 * @param passwordInput
	 * @return
	 */
	boolean validatePassword(String userId, String passwordInput);
	
	
	/**
	 * 
	 * 使用user name查找
	 * @param username
	 * @return
	 */
	SysUser findByUserName(String username);
}