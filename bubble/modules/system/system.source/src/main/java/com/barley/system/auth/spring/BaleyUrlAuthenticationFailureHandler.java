package com.barley.system.auth.spring;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jline.internal.Log;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: BaleyUrlAuthenticationFailureHandler.java, V1.0.0 2021年1月2日
 *          上午10:35:02 $
 */
@Slf4j
public class BaleyUrlAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		Log.info("=================================");
		log.info("validate failed ");
		Log.info("=================================");
		
	}

}
