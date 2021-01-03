package com.barley.system.service.auth;

import java.util.List;

import org.barley.mybatis.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barley.system.mappers.RoleMapper;
import com.barley.system.modal.Role;
import com.barley.system.modal.RoleWapper;
import com.barley.system.service.auth.searchvo.RoleSearchVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author peculiar.1@163.com
 * @version $ID: com.barley.system.service.auth.RoleBaseService create date
 *          2020-12-30 21:58:50
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	@Autowired
	protected RoleMapper entityMapper;

	public Role create(Role record) {
		entityMapper.insert(record);
		return record;
	}

	public void delete(Integer keyId) {
		entityMapper.deleteByPrimaryKey(keyId);
	}

	public Role update(Role record) {
		entityMapper.updateByPrimaryKey(record);
		return record;
	}

	public List<Role> findAll() {
		RoleSearchVO searchvo = new RoleSearchVO();
		return entityMapper.searchByCriteria(searchvo);
	}

	public Role findByPrimaryKey(Integer keyId) {
		Role entity = entityMapper.selectByPrimaryKey(keyId);
		return entity;
	}

	public List<Role> searchByVO(RoleSearchVO searchVO) {
		PageInfo<Role> pageInfo = internalfindBySearchVO(searchVO, null, null);
		return pageInfo.getList();
	}

	public PageInfo<Role> searchByVO(RoleSearchVO searchVO, int page, int pageSize) {
		return internalfindBySearchVO(searchVO, page, pageSize);
	}

	protected PageInfo<Role> internalfindBySearchVO(RoleSearchVO searchvo, Integer page, Integer pageSize) {
		Page<Object> pagesvo = null;
		if (page != null) {
			pagesvo = PageHelper.startPage(page, pageSize);
		}
		if (searchvo instanceof CriteriaBuilder) {
			((CriteriaBuilder) searchvo).build();
		}
		List<Role> list = entityMapper.searchByCriteria(searchvo);
		PageInfo<Role> pageInfo = new PageInfo<Role>(list, pageSize);
		if (pagesvo != null) {
			pageInfo.setTotal(pagesvo.getTotal());
		}
		return pageInfo;
	}

	/**
	 * 
	 */
	@Override
	public List<RoleWapper> findByGroup(Integer groupId) {
		return entityMapper.findByGroup(groupId);
	}

	@Override
	public List<Role> findByUserId(String userId) {
		return entityMapper.findByUserId(userId);
	}

	/**
	 * roles 直接挂载在当前用户下的role. (非其他方式，通过profile等)
	 */
	@Override
	public List<RoleWapper> loadingByUser(String userId) {
		return entityMapper.loadingByUser(userId);
	}
}