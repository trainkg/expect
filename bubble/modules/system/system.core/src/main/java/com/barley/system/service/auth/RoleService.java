package com.barley.system.service.auth;

import java.util.List;

import com.barley.system.modal.Role;
import com.barley.system.modal.RoleWapper;

/**
 * @author peculiar.1@163.com
 * @version $ID: com.barley.system.service.auth.RoleBaseService create date 2020-12-30 21:58:50
 */
public interface RoleService extends RoleBaseService {
	
	/**
	 * 
	 * loading roles in corresponding group. 
	 * @param groupId
	 * @return
	 */
	List<RoleWapper> findByGroup(Integer groupId);
	
	/**
	 * 
	 * find user's group configurations.
	 * @param userId
	 * @return
	 */
	List<Role> findByUserId(String  userId);
	
	/**
	 * 
	 * loading user roles authority.
	 * @param userId
	 * @return
	 */
	List<RoleWapper> loadingByUser(String userId);
}