package com.example.service;


import com.example.entity.User;

public interface UserService {

    User login(User user);

    User findByUserName(String username);

    void register(String name, String password);
}

