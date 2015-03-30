package com.alex.authenticator.cotroller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="admin")
public class AdminController {
	private static final Log LOGGER = LogFactory
			.getLog(AdminController.class);
	
	@RequestMapping(params = "user")
	public String user() {
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser.isPermitted("admin.do?user")){
			LOGGER.info("AdminController---permission");
			return "/views/user";	
		}else{
			LOGGER.info("AdminController---nopermission");
			return "errors/403";
		}
	}
	
	@RequestMapping(params = "version")
	public String version() {
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser.isPermitted("admin.do?version")){
			LOGGER.info("AdminController---permission");
			return "/views/version";	
		}else{
			LOGGER.info("AdminController---nopermission");
			return "errors/403";
		}
	}
}
