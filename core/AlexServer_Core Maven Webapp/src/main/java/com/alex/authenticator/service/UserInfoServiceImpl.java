package com.alex.authenticator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alex.authenticator.dao.UserRoleMapper;
import com.alex.authenticator.model.UserInfo;
import com.alex.authenticator.model.UserRoleLink;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoServiceI {

	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Override
	public UserInfo getUserByName(String username) {
		UserInfo user = userRoleMapper.findUserByUserName(username);
		return user;
	}

	@Override
	public Boolean modifyUserRole(UserRoleLink userRoleLink) {
		return userRoleMapper.insertUserRoleRelation(userRoleLink);
	}

}
