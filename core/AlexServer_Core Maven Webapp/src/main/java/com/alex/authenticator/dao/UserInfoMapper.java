package com.alex.authenticator.dao;

import com.alex.authenticator.model.UserInfo;

public interface UserInfoMapper {
	 UserInfo selectByUserName(String userName);
}
