package com.alex.web.service;

import java.util.List;

import com.alex.web.domain.User;



public interface UserServiceI {

    /**
     * 添加用户
     * @param user
     */
    void addUser(User user);
    
    /**
     * 根据用户id获取用户
     * @param userId
     * @return
     */
    User getUserById(String userId);
    
    /**获取所有用户信息
     * @return List<User>
     */
    List<User> getAllUser();
    
    /**
     * 分页获取所有用户信息
     * @return
     */
    List<User> getAllUserByPage(int pageNum, int pageSize);
}
