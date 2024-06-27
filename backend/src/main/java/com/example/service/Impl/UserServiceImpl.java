package com.example.service.Impl;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service

    public class UserServiceImpl implements UserService{

    @Autowired

    private UserMapper userMapper;
        @Override
        @Transactional(propagation = Propagation.SUPPORTS)
        public User login(User user) {
            // 根据接收用户名密码查询数据库
            User userDB = userMapper.selectUser(user);
            if (user!=null){
                return userDB;
            }
            throw  new RuntimeException("登录失败 -.-");
        }
    }
