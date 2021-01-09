package com.barley.system.auth.spring;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


/**
 * no doc.
 * @author peculiar.1@163.com
 * @version $ID: DeniedResponse.java, V1.0.0 2021年1月9日 下午3:53:27 $
 */
@Getter
@Setter
@AllArgsConstructor
public class DeniedResponse {

	private HttpStatus httpStatus;
	private String message;
	
}
