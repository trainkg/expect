package com.barley.system.auth.spring;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: BlyLogoutHandler.java, V1.0.0 2021年1月7日 下午9:44:25 $
 */

@Slf4j
public class BlyLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
	
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		super.onLogoutSuccess(request, response, authentication);
		log.info("authentication detail [{}]", authentication.getDetails());
	}

}
