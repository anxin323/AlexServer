package com.alex.authenticator.service;

import com.alex.authenticator.model.UserInfo;
import com.alex.authenticator.model.UserRoleLink;

public interface UserInfoServiceI {

	UserInfo getUserByName(String username);
	Boolean modifyUserRole(UserRoleLink userRoleLink);
}
