package com.barley.system.controller.app.handler;

import java.util.ArrayList;
import java.util.List;

import com.barley.system.application.LoginContext;
import com.barley.system.application.LoginException;
import com.barley.system.application.LoginHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Application
 * 
 * @author peculiar.1@163.com
 * @version $ID: LoginHanderAggregator.java, V1.0.0 2020年12月31日 上午11:08:50 $
 */
@Slf4j
public class DelegatingLoginHander implements LoginHandler {

	private List<LoginHandler> handlers = new ArrayList<LoginHandler>(10);

	@Override
	public void login(LoginContext context) throws LoginException {
		handlers.stream().forEach(action -> {
			log.info("execute login hander {} ", action);
			action.login(context);
		});
	}

	/**
	 * 
	 * for extend
	 * 
	 * @param handler
	 */
	public synchronized void addHanlder(LoginHandler handler) {
		handlers.add(handler);
	}

	public void setHandlers(List<LoginHandler> handlers) {
		this.handlers = handlers;
	}
}
