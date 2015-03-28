package com.alex.authenticator.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alex.authenticator.dao.UserInfoMapper;
import com.alex.authenticator.model.UserInfo;
import com.alex.authenticator.utils.EncryptUtils;
import com.alex.web.domain.User;

@Service("monitorRealm")
public class MonitorRealm extends AuthorizingRealm {
	private static final Log LOGGER = LogFactory.getLog(MonitorRealm.class);
	/*
	 * @Autowired UserService userService;
	 * 
	 * @Autowired RoleService roleService;
	 * 
	 * @Autowired LoginLogService loginLogService;
	 */

	@Autowired
	private UserInfoMapper userDao;
	
	public MonitorRealm() {
		super();

	}

	/**
	 * 鉴权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String userName = (String) principals.fromRealm(getName()).iterator().next(); 
		
//		User user = accountManager.findUserByUserName(userName);  
//        if (user != null) {  
//            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();  
//            for (Group group : user.getGroupList()) {  
//                info.addStringPermissions(group.getPermissionList());  
//            }  
//            return info;  
//        } else {  
//            return null;  
//        }  
		
		LOGGER.debug("*******doGetAuthorizationInfo userName=" + userName);
		/* 这里编写授权代码 */
		Set<String> roleNames = new HashSet<String>();
	    Set<String> permissions = new HashSet<String>();
	    roleNames.add("admin");
	    permissions.add("user.do?myjsp");
	    permissions.add("login.do?main");
	    permissions.add("login.do?logout");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
	    info.setStringPermissions(permissions);
		return info;

	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		/* 这里编写认证代码 */
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String username = String.valueOf(token.getUsername());
		String password = String.valueOf(token.getPassword());
		LOGGER.debug("get from client:username="+username+" password="+ password);
		UserInfo user = userDao.selectByUserName(username);
	
		 AuthenticationInfo authenticationInfo = null;
		 if (null != user) {
			    LOGGER.debug("get from db:username="+user.getUserName()+" password="+user.getPassword());
	            if (password.equals(user.getPassword())) {
	                authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
	            }
	        }
		 LOGGER.debug("get from db:username=null");
		return authenticationInfo;
//		return new SimpleAuthenticationInfo(user.getUserName(),
//				user.getPassword(), getName());


	}

	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

}
