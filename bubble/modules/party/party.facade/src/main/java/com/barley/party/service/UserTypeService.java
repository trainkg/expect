package com.barley.party.service;

import java.util.List;

import com.barley.party.modal.UserType;

/**
 * @author peculiar.1@163.com
 * @version $ID: com.barley.party.service.UserTypeBaseService create date 2020-12-27 15:01:11
 */
public interface UserTypeService extends UserTypeBaseService {
	public List<UserType> findAllActive();
}