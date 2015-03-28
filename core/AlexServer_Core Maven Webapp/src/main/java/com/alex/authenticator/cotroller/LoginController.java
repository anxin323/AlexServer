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
import org.springframework.web.servlet.ModelAndView;

import com.alex.authenticator.model.UserInfo;
import com.alex.authenticator.utils.EncryptUtils;
import com.alex.web.controller.UserInfoController;

@Controller
@RequestMapping(value = "login")
public class LoginController {
	private static final Log LOGGER = LogFactory
			.getLog(UserInfoController.class);

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
			view = "/login";
			e.printStackTrace();

		}
		if (currentUser.isAuthenticated()) {
			user.setUserName("张三");
			session.setAttribute("userinfo", user);
			// view="redirect:/getUser.do";
			view = "/main";
			// response.sendRedirect("getUser.do");
		} else {
			request.setAttribute("message", "用户名密码错误，请重新登陆!");
			view = "/login";
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
		return "/login";
	}

	@RequestMapping(params = "report")
	public String report() {

		LOGGER.debug("Controller：数据统计");
		return "/report";
	}

	@RequestMapping(params = "version")
	public String version(HttpSession session) {

		if (session.getAttribute("userinfo")==null){
			LOGGER.debug("Controller：版本升级-未登录");
			return "/login";
		}
		LOGGER.debug("Controller：版本升级");
		return "/version";
	}
	
	@RequestMapping(params = "message")
	public String message(HttpSession session) {

		if (session.getAttribute("userinfo")==null){
			LOGGER.debug("Controller：消息设置-未登录");
			return "/login";
		}
		LOGGER.debug("Controller：消息设置");
		return "/message";
	}
	
	@RequestMapping(params = "online")
	public String online(HttpSession session) {
		if (session.getAttribute("userinfo")==null){
			LOGGER.debug("Controller：在线用户-未登录");
			return "/login";
		}
		LOGGER.debug("Controller：在线用户");
		return "/online";
	}
}
