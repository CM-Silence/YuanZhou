package com.example.service;

import com.example.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> selectAll();

    List<Map<String, Object>> selectUserScore();
}

