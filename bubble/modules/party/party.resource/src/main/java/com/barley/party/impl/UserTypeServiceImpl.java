package com.barley.party.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barley.party.mappers.UserTypeMapper;
import com.barley.party.modal.UserType;
import com.barley.party.services.UserTypeService;
import com.barley.party.vo.UserTypeSearchVO;

@Service
@Transactional
public class UserTypeServiceImpl implements UserTypeService {

    @Override
    public void createUserType(UserType userType) {
        daoUserType.insert(userType);
    }

    @Override
    public UserType findById(Short id) {
        return daoUserType.selectByPrimaryKey(id);
    }


    @Override
    public List<UserType> findAllActive() {
        UserTypeSearchVO searchvo = new UserTypeSearchVO();
        short status = 1;
        searchvo.setTypeStatus(status);
        searchvo.pushToCriteria();
        return daoUserType.searchByCriteria(searchvo);
    }

    @Override
    public List<UserType> findAll() {
        UserTypeSearchVO searchvo = new UserTypeSearchVO();
        return daoUserType.searchByCriteria(searchvo);
    }

    @Override
    public void deleteById(Short id) {
        daoUserType.deleteByPrimaryKey(id);
    }

    @Override
    public void updateUserType(UserType userType) {
        daoUserType.updateByPrimaryKey(userType);
    }

    @Autowired
    private UserTypeMapper daoUserType;
}
