package com.barley.system.controller.app;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.barley.system.application.LoginContext;
import com.barley.system.service.app.LoginService;

/**
 * 
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: LoginController.java, V1.0.0 2020年12月31日 上午10:12:07 $
 */

@Controller
public class LoginController {

	/**
	 * 
	 * goto login page.
	 * 
	 * @return
	 */
	@GetMapping("/login.htm")
	public String gotoLoginPage(HttpServletRequest request) {
		// error information
		
		return "login";
	}

	/**
	 * goto home page
	 * 
	 * @return
	 */
	@GetMapping("/home.htm")
	public String gotoHomePage(HttpServletRequest request) {
		return "home";
	}

	/**
	 * 
	 * this page show no authority tips
	 * 
	 * @param request
	 * @return
	 */
	@GetMapping("/noauth.htm")
	public String gotoNoAuthPage(HttpServletRequest request) {
		return "no_auth_tip";
	}

	/**
	 * 
	 * logout
	 * 
	 * @param request
	 * @throws ServletException----------------------------------------------------------------------------------------------------------------------------------------------------
	 */
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request) throws ServletException {
		request.logout();
	}

	/**
	 * 
	 * 
	 * system login controller
	 * 
	 * @param form
	 */
	@PostMapping("/login")
	@ResponseBody
	public void login(@ModelAttribute LoginForm form, HttpSession session, Errors errors) {
		validate.validate(form, errors);
		if (!errors.hasErrors()) {
			String userId = "";
			if (LoginType.password.equals(form.getType())) {
				if (validatePassword(form, userId)) {

				} else {
					errors.reject("password", "密码或者用户名不正确");
				}
			} else if (LoginType.token.equals(form.getType())) {
				if (validateToken(form, userId)) {

				} else {
					errors.reject("token", "无效的token");
				}
			}

			LoginContext context = buildLoginContext(session, userId);
			servLogin.login(context);
		}
	}

	private LoginContext buildLoginContext(HttpSession session, String userId) {
		LoginContext context = new LoginContext();
		context.setUserId(userId);
		context.setSession(session);
		return context;
	}

	private boolean validateToken(LoginForm form, String userId) {
		return false;
	}

	private boolean validatePassword(LoginForm form, String userId) {
		return false;
	}

	private LoginFormValidator validate = new LoginFormValidator();

	@Autowired
	private LoginService servLogin;

}
