package com.alex.authenticator.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.alex.authenticator.model.UserInfo;

@Component
public interface UserRoleMapper {
	UserInfo findUserByUserName(@Param(value="username")String username);
}
