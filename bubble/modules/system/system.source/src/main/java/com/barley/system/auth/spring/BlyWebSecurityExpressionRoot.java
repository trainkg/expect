package com.barley.system.auth.spring;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;

/**
 * 
 * BlyWebSecurityExpressionRoot 提供权限表达式application级别扩展类
 * 
 * @author peculiar.1@163.com
 * @version $ID: BlyWebSecurityExpressionRoot.java, V1.0.0 2021年1月5日 下午9:36:32 $
 */
public class BlyWebSecurityExpressionRoot extends WebSecurityExpressionRoot {

	private String defaultPremPrefix;
	private RoleHierarchy roleHierarchy;

	public BlyWebSecurityExpressionRoot(Authentication a, FilterInvocation fi) {
		super(a, fi);
	}

	@Override
	public void setRoleHierarchy(RoleHierarchy roleHierarchy) {
		super.setRoleHierarchy(roleHierarchy);
		this.roleHierarchy = roleHierarchy;
	}

	/**
	 * 
	 * @param authPrem
	 */
	public boolean hasAuthPerm(String authPrem) {
		return hasAnyAuthorityName(defaultPremPrefix, authPrem);
	}

	public void setDefaultPremPrefix(String defaultPremPrefix) {
		this.defaultPremPrefix = defaultPremPrefix;
	}

	private boolean hasAnyAuthorityName(String prefix, String... authPrem) {
		Set<String> premSet = getAuthoritySet();

		for (String prem : authPrem) {
			String defaultedPrem = getPremWithDefaultPrefix(prefix, prem);
			if (premSet.contains(defaultedPrem)) {
				return true;
			}
		}

		return false;
	}

	private static String getPremWithDefaultPrefix(String defaultPremPrefix, String prem) {
		if (prem == null) {
			return prem;
		}
		if (defaultPremPrefix == null || defaultPremPrefix.length() == 0) {
			return prem;
		}
		if (prem.startsWith(defaultPremPrefix)) {
			return prem;
		}
		return defaultPremPrefix + prem;
	}

	private Set<String> getAuthoritySet() {
		Set<String> roles = null;
		if (roles == null) {
			Collection<? extends GrantedAuthority> userAuthorities = authentication.getAuthorities();

			if (roleHierarchy != null) {
				userAuthorities = roleHierarchy.getReachableGrantedAuthorities(userAuthorities);
			}

			roles = AuthorityUtils.authorityListToSet(userAuthorities);
		}

		return roles;
	}
}
