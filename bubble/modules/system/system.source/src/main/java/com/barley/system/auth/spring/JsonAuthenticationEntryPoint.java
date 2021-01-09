package com.barley.system.auth.spring;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Setter;

/**
 * <p>
 * JsonAuthenticationEntryPoint 用于权限异常情况提供JSON形式的数据返回
 * </p>
 * 
 * @author peculiar.1@163.com
 * @version $ID: JsonAuthenticationEntryPoint.java, V1.0.0 2021年1月9日 下午4:17:40 $
 */
public class JsonAuthenticationEntryPoint implements AuthenticationEntryPoint, ApplicationContextAware {

	private ApplicationContext applicationContext;
	@Setter
	private ObjectMapper objectMapper;
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) {

		HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
		response.setStatus(httpStatus.value());
		System.out.println(applicationContext);
		DeniedResponse deniedResponse = new DeniedResponse(httpStatus, authException.getMessage());
		try {
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.getWriter().write(objectMapper.writeValueAsString(deniedResponse));
			response.flushBuffer();
		} catch (IOException e) {
			// ignore
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
