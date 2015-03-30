package com.alex.authenticator.cotroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alex.authenticator.model.UserInfo;
import com.alex.authenticator.utils.EncryptUtils;

@Controller
@RequestMapping(value = "login")
public class LoginController {
	private static final Log LOGGER = LogFactory
			.getLog(LoginController.class);

	/*
	 * @Autowired User user;
	 */
	/**
	 * 用户登录
	 * 
	 * @param user
	 *            　登录用户
	 * @return
	 */
	@RequestMapping(params = "main")
	public String login(UserInfo user, HttpSession session,
			HttpServletRequest request) {
		LOGGER.debug("Login Cotroller is running user=" + user.getPassword()
				+ "-" + user.getUserName() + "-" + user.getUsercode());
		String view = "";
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(
				user.getUserName(), EncryptUtils.encryptMD5(user.getPassword()));
		token.setRememberMe(true);
		try {
			currentUser.login(token);
		} catch (AuthenticationException e) {
			request.setAttribute("message", "用户名密码错误，请重新登陆!");
			view = "/index";
			e.printStackTrace();

		}
		if (currentUser.isAuthenticated()) {
			user.setUserName("张三");
			session.setAttribute("userinfo", user);
			// view="redirect:/getUser.do";
			view = "/views/main";
			// response.sendRedirect("getUser.do");
		} else {
			request.setAttribute("message", "用户名密码错误，请重新登陆!");
			view = "/index";
		}
		LOGGER.debug("Login Cotroller return:" + view);
		return view;
	}

	/**
	 * 退出登录
	 * 
	 * @return
	 */
	@RequestMapping(params = "logout")
	public String logout() {
		LOGGER.debug("User is Logout");
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.logout();
		} catch (AuthenticationException e) {
			e.printStackTrace();

		}
		LOGGER.debug("Login Cotroller return:index");
		return "/index";
	}

	
}
