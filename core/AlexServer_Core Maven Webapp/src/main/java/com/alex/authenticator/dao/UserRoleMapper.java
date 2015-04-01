package com.alex.authenticator.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.alex.authenticator.model.Role;
import com.alex.authenticator.model.UserInfo;
import com.alex.authenticator.model.UserRoleLink;

@Component
public interface UserRoleMapper {
	UserInfo findUserByUserName(@Param(value="username")String username);
	
	Boolean deleteUserRoleRelation(@Param(value="userRoleLink")UserRoleLink userRoleLink);
	
	Boolean insertUserRoleRelation(@Param(value="userRoleLink")UserRoleLink userRoleLink);
}
