package com.barley.system.controller.app;

import org.apache.commons.lang3.StringUtils;
import org.barley.exception.BarleyException;
import org.barley.exception.ExceptionCode;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * 
 * login form validate
 * 
 * @author peculiar.1@163.com
 * @version $ID: LoginFormValidator.java, V1.0.0 2020年12月31日 上午11:37:52 $
 */
public class LoginFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return LoginForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LoginForm form = (LoginForm) target;
		LoginType loginType = form.getType();
		if (loginType == null) {
			throw new BarleyException(ExceptionCode.ERR_ILLEGAL_REQUEST, "login type is empty.");
		}

		if (LoginType.password.equals(loginType)) {
			validatePassworModel(form, errors);
		} else if (LoginType.token.equals(loginType)) {
			validateTokenModel(form, errors);
		} else {
			throw new BarleyException(ExceptionCode.ERR_ILLEGAL_REQUEST, "login type is empty.");
		}
	}

	/**
	 * 
	 * validate
	 * 
	 * @param form
	 * @param errors
	 */
	private void validateTokenModel(LoginForm form, Errors errors) {

		if (StringUtils.isEmpty(form.getUserName())) {
			errors.reject("username", "登陆信息为空");
		}

		if (StringUtils.isEmpty(form.getPassword())) {
			errors.reject("password", "密码信息为空");
		}

	}

	private void validatePassworModel(LoginForm form, Errors errors) {
		// TODO
	}

}
