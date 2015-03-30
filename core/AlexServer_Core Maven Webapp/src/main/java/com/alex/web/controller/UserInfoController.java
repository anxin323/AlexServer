package com.alex.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alex.web.domain.User;
import com.alex.web.service.UserServiceI;
import com.wordnik.swagger.annotations.ApiOperation;


@Controller  
@Scope("prototype")  
public class UserInfoController {
	private static final Log LOGGER = LogFactory.getLog(UserInfoController.class);

	@Autowired
	private UserServiceI userService;
	  
    @RequestMapping(value="/getUser")  
    //@ApiOperation(value = "获取所有的用户信息", httpMethod = "POST", notes = "用户信息以json格式传递", response = String.class)
    public String user(HttpServletRequest request, HttpServletResponse response){  
      
    	LOGGER.debug("cotronller--getUser is running.");
        //获取所有的用户信息
        //List<User> lstUsers = userService.getAllUser();
    	int pageNum = Integer.parseInt(request.getParameter("pageNum"));
    	int pageSize = 12;//Integer.parseInt(request.getParameter("pageSize"));
    	List<User> lstUsers = userService.getAllUserByPage(pageNum, pageSize);
    	
        request.setAttribute("lstUsers", lstUsers);
		return "views/user";
    } 
    
  
}
