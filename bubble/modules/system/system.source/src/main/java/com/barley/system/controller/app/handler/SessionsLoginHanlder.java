package com.barley.system.controller.app.handler;

import com.barley.system.application.CurrentUser;
import com.barley.system.application.LoginContext;
import com.barley.system.application.LoginException;
import com.barley.system.application.LoginHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * add user info to Session.
 * 
 * @author peculiar.1@163.com
 * @version $ID: SessionsLoginHanlder.java, V1.0.0 2020年12月31日 下午1:17:34 $
 */
@Slf4j
public class SessionsLoginHanlder implements LoginHandler {

	@Override
	public void login(LoginContext context) throws LoginException {
		log.info("SessionsLoginHanlder execute");
		CurrentUser user = buildCurrentUser(context);
		context.getSession().setAttribute(LoginConstants.SESSION_KEY, user);
	}

	private CurrentUser buildCurrentUser(LoginContext context) {
		CurrentUser user = new CurrentUser();
		user.setSysuser(context.getSysuser());
		return user;
	}

}
