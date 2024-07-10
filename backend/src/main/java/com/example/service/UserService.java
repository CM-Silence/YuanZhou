package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.User;

public interface UserService extends IService<User> {

    User login(User user);

    User findByUserName(String username);

    void register(String username, String password, Integer permission,  String phone, String email, String class_name, String occupational_type);
}

