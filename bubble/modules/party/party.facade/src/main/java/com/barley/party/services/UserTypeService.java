package com.barley.party.services;

import java.util.List;

import com.barley.party.modal.UserType;

/**
 * @author peculiar.1@163.com
 * @version $ID: UserTypeService.java, V1.0.0 2020年5月28日 下午9:50:41 $
 */
public interface UserTypeService {
    
    public void createUserType(UserType userType) ;
    
    public UserType findById(Short id);
    
    public List<UserType> findAllActive();
    
    public List<UserType> findAll();
    
    public void deleteById(Short id);
    
    public void updateUserType(UserType user);
}
