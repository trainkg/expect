package com.barley.system.auth.spring;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.barley.system.modal.Group;
import com.barley.system.modal.GroupWapper;
import com.barley.system.modal.RoleWapper;
import com.barley.system.modal.SysUser;
import com.barley.system.service.auth.GroupService;
import com.barley.system.service.auth.RoleService;
import com.barley.system.service.base.SysUserService;

/**
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: BarleyUserDetailService.java, V1.0.0 2021年1月1日 下午9:20:30 $
 */
public class UserDetailService implements UserDetailsService {

	/**
	 * PROFILE
	 */
	public static final String AUTHORITY_TYPE_PROFILE = "$G_";

	/**
	 * ROLE
	 */
	public static final String AUTHORITY_TYPE_ROLE = "$R_";

	/**
	 * PREMISSION
	 */
	public static final String AUTHORITY_TYPE_PREM = "$P_";

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser user = servUser.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("BarleyUserDetailService username " + username + " not found");
		}
		return buildUser(user);
	}

	private UserDetails buildUser(SysUser user) {

		boolean active = 1 == user.getUserStatus().intValue();
		boolean notExpiry = StringUtils.isBlank(user.getExpired()) || "N".equals(user.getExpired());
		boolean pwdExpiry = StringUtils.isBlank(user.getPwdExpired()) || "N".equals(user.getPwdExpired());
		boolean accountLocked = StringUtils.isBlank(user.getLocked()) || "N".equals(user.getLocked());

		List<GrantedAuthority> list = loadingGrantedAuthority(user);
		UserDetails detail = new User(user.getLoginName(), user.getPassword(), active, notExpiry, pwdExpiry,
				accountLocked, list);
		return detail;
	}

	/**
	 * 
	 * loading user authority.
	 * 
	 * @param user
	 * @return
	 */
	private List<GrantedAuthority> loadingGrantedAuthority(SysUser user) {
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		List<Group> groups = servGroup.findByUserId(user.getId());
		List<Integer> inListRoles = new ArrayList<Integer>();

		// user profiles
		if(groups != null) {
			for (Group group : groups) {
				auths.add(new SimpleGrantedAuthority(AUTHORITY_TYPE_PROFILE + group.getName()));
				GroupWapper groupWapper = servGroup.loadingGroup(group.getListId());
				if (groupWapper.getRoleList() != null) {
					groupWapper.getRoleList().forEach(rolewapper -> {
						inListRoles.add(rolewapper.getRole().getListId());
						addRoelAuthority(rolewapper, auths);
					});
				}
			}
		}
		

		// user roles
		List<RoleWapper> roles = servRole.loadingByUser(user.getId());
		if(roles != null) {
			roles.forEach(rolewapper -> {
				if (!inListRoles.contains(rolewapper.getRole().getListId())) {
					addRoelAuthority(rolewapper, auths);
				}
			});			
		}
		inListRoles.clear();
		return auths;
	}

	private void addRoelAuthority(RoleWapper rolewapper, List<GrantedAuthority> auths) {
		auths.add(new SimpleGrantedAuthority(AUTHORITY_TYPE_ROLE + rolewapper.getRole().getName()));
		if (rolewapper.getPermissions() != null) {
			rolewapper.getPermissions().forEach(permission -> {
				SimpleGrantedAuthority auth = new SimpleGrantedAuthority(AUTHORITY_TYPE_PREM + permission.getCode());
				if(!auths.contains(auth)) {
					auths.add(auth);
				}
			});
		}
	}

	@Autowired
	private SysUserService servUser;
	@Autowired
	private GroupService servGroup;
	@Autowired
	private RoleService servRole;

}
