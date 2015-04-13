package com.alex.authenticator.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alex.web.controller.UserInfoController;
import com.alex.web.domain.User;
import com.alex.web.service.UserServiceI;

@Controller
@RequestMapping(value="user")
public class UserController {
	private static final Log LOGGER = LogFactory
			.getLog(UserController.class);
	
	@Autowired
	private UserServiceI userService;
	
	@RequestMapping(params = "main")
	public String main() {
		LOGGER.debug("Controller：数据统计  main=/views/report");
		return "/views/main";	
	}
	
	
	@RequestMapping(params = "report")
	public String report() {
		LOGGER.debug("Controller：数据统计  return=/views/report");
		return "/views/report";	
	}

	@RequestMapping(params = "version")
	public String version(HttpSession session) {

//		if (session.getAttribute("userinfo")==null){
//			LOGGER.debug("Controller：版本升级-未登录");
//			return "/login";
//		}
		LOGGER.debug("Controller：版本升级    return=/views/version");
		return "/views/version";
	}
	
	@RequestMapping(params = "message")
	public String message(HttpSession session) {

//		if (session.getAttribute("userinfo")==null){
//			LOGGER.debug("Controller：消息设置-未登录");
//			return "/login";
//		}
		LOGGER.debug("Controller：消息设置  return=/views/message");
		return "/views/message";
	}
	
	@RequestMapping(params = "online")
	public String online(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("cotronller--getUser is running.");
        //获取所有的用户信息
        //List<User> lstUsers = userService.getAllUser();
    	int pageNum = Integer.parseInt(request.getParameter("pageNum"));
    	int pageSize = 12;//Integer.parseInt(request.getParameter("pageSize"));
    	List<User> lstUsers = userService.getAllUserByPage(pageNum, pageSize);
    	
        request.setAttribute("lstUsers", lstUsers);

		LOGGER.debug("Controller：在线用户  return=/views/online");
		return "/views/online";
	}
	
	/**
	 * 跳转到myjsp页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "myjsp")
	public String home() {
		LOGGER.info("UserController---home");
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser.isPermitted("user.do?myjsp")){
			LOGGER.info("UserController---my");
			return "my";
		}else{
			LOGGER.info("UserController---errors/403");
			return "errors/403";
		}
	}
	@RequestMapping(params = "notmyjsp")
	public String nopermission() {
		LOGGER.info("UserController---nopermission");
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser.isPermitted("user.do?notmyjsp")){
			return "notmyjsp";
		}else{
			return "errors/403";
		}
	}
}
