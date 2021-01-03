package com.barley.system.service.auth;

import java.util.List;

import com.barley.system.modal.Group;
import com.barley.system.modal.GroupWapper;

/**
 * @author peculiar.1@163.com
 * @version $ID: com.barley.system.service.auth.GroupBaseService create date 2021-01-03 11:09:28
 */
public interface GroupService extends GroupBaseService {
	
	/**
	 * 
	 * find by id.
	 * @return
	 */
	GroupWapper loadingGroup(Integer keyId);
	
	/**
	 * 
	 * find by user id
	 * @param userId
	 * @return
	 */
	List<Group> findByUserId(String userId);
}