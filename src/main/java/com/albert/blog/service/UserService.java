package com.albert.blog.service;

import com.albert.blog.pojo.User;

/**
 * 用户业务逻辑接口
 */
public interface UserService {

    // 根据用户名和密码登录
    public User loginByUsernameAndPassword(String username, String password);

    // 根据用户ID获取用户
    public User selectById(int id);

}
