package com.alex.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alex.web.dao.UserMapper;
import com.alex.web.domain.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


/**
 * 使用@Service注解将UserServiceImpl类标注为一个service
 * service的id是userService
 */
@Service("userService")
public class UserServiceImpl implements UserServiceI {

    /**
     * 使用@Autowired注解标注userMapper变量，
     * 当需要使用UserMapper时，Spring就会自动注入UserMapper
     */
    @Autowired
    private UserMapper userMapper;//注入dao

    public void addUser(User user) {
        userMapper.insert(user);
    }

    public User getUserById(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

	public List<User> getAllUser() {
		return userMapper.getAllUser();
	}

	public List<User> getAllUserByPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		//Page<User> page = new Page<User>();
		return userMapper.getAllUser();
	}
}
