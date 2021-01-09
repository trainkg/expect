package com.barley.system.auth.spring;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

import com.barley.system.auth.core.AccessType;

/**
 * 
 * Api filter: API访问方式控制
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: AccessChannelFilter.java, V1.0.0 2021年1月9日 上午10:27:23 $
 */
public class AccessChannelFilter extends GenericFilterBean {

	/**
	 * global parameter setting.
	 */
	public static final String PARAM_ACCESS_TYPE = "__BARLEY_INTERNAL_ACCESS_TYPE__";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (matchRequestURL(request)) {
			AccessType accessType = judgeAccesssType(request);
			request.setAttribute(PARAM_ACCESS_TYPE, accessType);
			System.out.println("=-======================" + request);
		}
		chain.doFilter(request, response);
	}

	protected boolean matchRequestURL(ServletRequest request) {
		return true;
	}

	/**
	 * 
	 * 
	 * @param request
	 * @return
	 */
	protected AccessType judgeAccesssType(ServletRequest request) {
		return AccessType.RESTFUL;
	}

}
