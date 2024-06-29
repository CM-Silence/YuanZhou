package com.example.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

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

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public void register(String username, String password) {

        //创建用户对象并设置属性
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        //调用Mapper的insertUser方法插入用户
        int rowsAffected = userMapper.insertUser(user);
        if (rowsAffected <= 0) {
            //插入失败的情况
            throw new RuntimeException("注册失败，无法插入用户数据");
        }
    }
}
