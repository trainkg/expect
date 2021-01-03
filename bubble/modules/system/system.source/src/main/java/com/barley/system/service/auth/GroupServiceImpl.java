package com.barley.system.service.auth;

import com.barley.system.mappers.GroupMapper;
import com.barley.system.modal.Group;
import com.barley.system.modal.GroupWapper;
import com.barley.system.modal.RoleWapper;
import com.barley.system.service.auth.searchvo.GroupSearchVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.barley.mybatis.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author peculiar.1@163.com
 * @version $ID: com.barley.system.service.auth.GroupBaseService create date
 *          2021-01-03 11:09:28
 */
@Service
@Transactional
public class GroupServiceImpl implements GroupService {
	@Autowired
	protected GroupMapper entityMapper;
	@Autowired
	private RoleService serveRole;

	public Group create(Group record) {
		entityMapper.insert(record);
		return record;
	}

	public void delete(Integer keyId) {
		entityMapper.deleteByPrimaryKey(keyId);
	}

	public Group update(Group record) {
		entityMapper.updateByPrimaryKey(record);
		return record;
	}

	public List<Group> findAll() {
		GroupSearchVO searchvo = new GroupSearchVO();
		return entityMapper.searchByCriteria(searchvo);
	}

	public Group findByPrimaryKey(Integer keyId) {
		Group entity = entityMapper.selectByPrimaryKey(keyId);
		return entity;
	}

	public List<Group> searchByVO(GroupSearchVO searchVO) {
		PageInfo<Group> pageInfo = internalfindBySearchVO(searchVO, null, null);
		return pageInfo.getList();
	}

	public PageInfo<Group> searchByVO(GroupSearchVO searchVO, int page, int pageSize) {
		return internalfindBySearchVO(searchVO, page, pageSize);
	}

	protected PageInfo<Group> internalfindBySearchVO(GroupSearchVO searchvo, Integer page, Integer pageSize) {
		Page<Object> pagesvo = null;
		if (page != null) {
			pagesvo = PageHelper.startPage(page, pageSize);
		}
		if (searchvo instanceof CriteriaBuilder) {
			((CriteriaBuilder) searchvo).build();
		}
		List<Group> list = entityMapper.searchByCriteria(searchvo);
		PageInfo<Group> pageInfo = new PageInfo<Group>(list, pageSize);
		if (pagesvo != null) {
			pageInfo.setTotal(pagesvo.getTotal());
		}
		return pageInfo;
	}

	@Override
	public GroupWapper loadingGroup(Integer groupId) {
		List<RoleWapper> roleWapper = serveRole.findByGroup(groupId);
		Group group = findByPrimaryKey(groupId);
		GroupWapper wapper = new GroupWapper(group, roleWapper);
		return wapper;
	}

	@Override
	public List<Group> findByUserId(String userId) {
		return entityMapper.findByUserId(userId);
	}
}