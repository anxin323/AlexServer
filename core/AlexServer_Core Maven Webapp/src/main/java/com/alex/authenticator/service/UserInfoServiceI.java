package com.alex.authenticator.service;

import com.alex.authenticator.model.UserInfo;

public interface UserInfoServiceI {

	UserInfo getUserByName(String username);
}
