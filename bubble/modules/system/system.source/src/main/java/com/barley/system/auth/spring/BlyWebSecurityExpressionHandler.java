package com.barley.system.auth.spring;

import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

/**
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: SecurityExpressionHandler.java, V1.0.0 2021年1月5日 下午9:32:39 $
 */
public class BlyWebSecurityExpressionHandler extends DefaultWebSecurityExpressionHandler {

	private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

	private String defaultRolePrefix = "ROLE_";
	private String defaultPremPrefix = "PREM_";

	@Override
	protected SecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication,
			FilterInvocation fi) {
		BlyWebSecurityExpressionRoot root = new BlyWebSecurityExpressionRoot(authentication, fi);
		root.setPermissionEvaluator(getPermissionEvaluator());
		root.setTrustResolver(trustResolver);
		root.setRoleHierarchy(getRoleHierarchy());
		root.setDefaultRolePrefix(defaultRolePrefix);
		root.setDefaultPremPrefix(defaultPremPrefix);
		return root;
	}

	@Override
	public void setDefaultRolePrefix(String defaultRolePrefix) {
		this.defaultRolePrefix = defaultRolePrefix;
	}
	
	public void setDefaultPremPrefix(String defaultPremPrefix) {
		this.defaultPremPrefix = defaultPremPrefix;
	}
}
