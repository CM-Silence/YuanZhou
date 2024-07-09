package com.example.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.JWTUtils;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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
                Map<String, String> payload = new HashMap<>();
                payload.put("id", String.valueOf(userDB.getUid()));
                payload.put("username", userDB.getUsername());
                String token = JWTUtils.getToken(payload);
                // 更新用户对象中的token字段（如果需要）
                userDB.setToken(token);

                //更新login_at字段（需要当前时间）
                LocalDateTime now = LocalDateTime.now();
                userMapper.updateLoginAt(userDB.getUid(),now);

                // 返回用户对象（注意：这里不直接返回token，但你可以根据需要在响应中返回）
                return userDB;
            }
            throw new RuntimeException("登录失败 -.-");
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
        user.setName(user.getUsername());
        user.setPermission(1);

        //调用Mapper的insertUser方法插入用户
        int rowsAffected = userMapper.insert(user);

        if (rowsAffected <= 0) {
            //插入失败的情况
            throw new RuntimeException("注册失败，无法插入用户数据");
        }
    }
}
