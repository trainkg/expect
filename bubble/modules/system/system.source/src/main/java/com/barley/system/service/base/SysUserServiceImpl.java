package com.barley.system.service.base;

import java.util.List;

import org.barley.mybatis.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barley.system.mappers.SysUserMapper;
import com.barley.system.modal.SysUser;
import com.barley.system.service.base.searchvo.SysUserSearchVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author peculiar.1@163.com
 * @version $ID: com.barley.system.service.base.SysUserBaseService create date
 *          2020-12-31 12:21:20
 */
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	protected SysUserMapper entityMapper;

	public SysUser create(SysUser record) {
		entityMapper.insert(record);
		return record;
	}

	public void delete(String keyId) {
		entityMapper.deleteByPrimaryKey(keyId);
	}

	public SysUser update(SysUser record) {
		entityMapper.updateByPrimaryKey(record);
		return record;
	}

	public List<SysUser> findAll() {
		SysUserSearchVO searchvo = new SysUserSearchVO();
		return entityMapper.searchByCriteria(searchvo);
	}

	public SysUser findByPrimaryKey(String keyId) {
		SysUser entity = entityMapper.selectByPrimaryKey(keyId);
		return entity;
	}

	public List<SysUser> searchByVO(SysUserSearchVO searchVO) {
		PageInfo<SysUser> pageInfo = internalfindBySearchVO(searchVO, null, null);
		return pageInfo.getList();
	}

	public PageInfo<SysUser> searchByVO(SysUserSearchVO searchVO, int page, int pageSize) {
		return internalfindBySearchVO(searchVO, page, pageSize);
	}

	protected PageInfo<SysUser> internalfindBySearchVO(SysUserSearchVO searchvo, Integer page, Integer pageSize) {
		Page<Object> pagesvo = null;
		if (page != null) {
			pagesvo = PageHelper.startPage(page, pageSize);
		}
		if (searchvo instanceof CriteriaBuilder) {
			((CriteriaBuilder) searchvo).build();
		}
		List<SysUser> list = entityMapper.searchByCriteria(searchvo);
		PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(list, pageSize);
		if (pagesvo != null) {
			pageInfo.setTotal(pagesvo.getTotal());
		}
		return pageInfo;
	}

	@Override
	public boolean validatePassword(String userId, String passwordInput) {
		SysUser sysUser = findByPrimaryKey(userId);
		String encrypPwd = sysUser.getPassword();
		return passwordEncoder.matches(passwordInput, encrypPwd);
	}
	
	@Override
	public SysUser findByUserName(String username) {
		if(!"user".equals(username)) {
			return null;
		}
		//return entityMapper.searchByCriteria(example);
		SysUser user = new SysUser();
		user.setLoginName("trainkg");
		user.setId("00083961edb14be2873c864ffb0c1dd0");
		user.setPassword("1234567");
		user.setUserStatus(1);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return user;
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	

}