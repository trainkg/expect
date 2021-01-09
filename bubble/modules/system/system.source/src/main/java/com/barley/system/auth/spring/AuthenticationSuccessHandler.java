package com.barley.system.auth.spring;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.barley.system.application.LoginContext;
import com.barley.system.application.LoginHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * BarleyAuthenticationSuccessHandler
 * 
 * @author peculiar.1@163.com
 * @version $ID: BarleyAuthenticationSuccessHandler.java, V1.0.0 2021年1月4日
 *          下午8:32:01 $
 */
@Slf4j
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private LoginHandler successHanlder;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		super.onAuthenticationSuccess(request, response, authentication);
		logger.info("BarleyAuthenticationSuccessHandler execute");
		if (successHanlder != null) {
			LoginContext context = buildContext(request, response, authentication);
			successHanlder.login(context);
		}
	}

	private LoginContext buildContext(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		LoginContext context = new LoginContext();
		context.setRequest(request);
		log.info("Authentication detail is [{}]", authentication.getDetails());
		return context;
	}
	
	public void setSuccessHanlder(LoginHandler successHanlder) {
		this.successHanlder = successHanlder;
	}
}
