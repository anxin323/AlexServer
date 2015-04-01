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
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alex.authenticator.dao.UserInfoMapper;
import com.alex.authenticator.dao.UserRoleMapper;
import com.alex.authenticator.model.Permission;
import com.alex.authenticator.model.Role;
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

	@Autowired
	private UserRoleMapper userRoleDao;

	public MonitorRealm() {
		super();

	}

	/**
	 * 鉴权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String userName = (String) principals.fromRealm(getName()).iterator()
				.next();

		// User user = accountManager.findUserByUserName(userName);
		// if (user != null) {
		// SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// for (Group group : user.getGroupList()) {
		// info.addStringPermissions(group.getPermissionList());
		// }
		// return info;
		// } else {
		// return null;
		// }

		LOGGER.debug("*******doGetAuthorizationInfo userName=" + userName);
		/* 这里编写授权代码 */
		Set<String> roleNames = new HashSet<String>();
		Set<String> permissions = new HashSet<String>();
		UserInfo userInfo = userRoleDao.findUserByUserName(userName);
		if (null == userInfo) {
			return new SimpleAuthorizationInfo();
		}
		for (Role role : userInfo.getRole()) {
			LOGGER.debug("roleName=" + role.getName());
			roleNames.add(role.getName());
			for (Permission permission : role.getPermission()) {
				permissions.add(permission.getName());
				LOGGER.debug("permissionName=" + permission.getName());
				LOGGER.debug("permissionDesc=" + permission.getDescription());
			}
		}
		// roleNames.add("admin");
		// permissions.add("admin.do?version");
		// permissions.add("admin.do?user");
		// permissions.add("login.do?logout");
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
		LOGGER.debug("get from client:username=" + username + " password="
				+ password);
		UserInfo user = userDao.selectByUserName(username);

		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				user.getUserName(), // 用户名
				user.getPassword(), // 密码
				getName() // realm name
		);
		LOGGER.debug("SimpleAuthenticationInfo");
		return authenticationInfo;

		// AuthenticationInfo authenticationInfo = null;
		// if (null != user) {
		// LOGGER.debug("get from db:username="+user.getUserName()+" password="+user.getPassword());
		// if (password.equals(user.getPassword())) {
		// authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(),
		// user.getPassword(), getName());
		// }
		// }
		// return authenticationInfo;

	}

	/**
	 * 清除缓存的方法，用于数据更新时清除，从而实现数据同步
	 * 
	 * @param principal
	 */
//	public void clearCachedAuthorizationInfo(String principal) {
//		SimplePrincipalCollection principals = new SimplePrincipalCollection(
//				principal, getName());
//		clearCachedAuthorizationInfo(principals);
//	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

}
