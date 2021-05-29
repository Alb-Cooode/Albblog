package com.albert.blog.service.serviceImpl;

import com.albert.blog.mapper.UserMapper;
import com.albert.blog.pojo.User;
import com.albert.blog.service.UserService;
import com.albert.blog.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取数据库中的用户名和密码，通过控制器传递过来的密码进行解析匹配
     * @param username 用户名
     * @param password 密码
     * @return 用户对象
     */
    @Override
    public User loginByUsernameAndPassword(String username, String password) {
        return this.userMapper.getUserByUsernameAndPassword(username, MD5Utils.code(password));
    }

    @Override
    public User selectById(int id) {
        return this.userMapper.getUserById(id);
    }
}
