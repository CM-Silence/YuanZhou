package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

//告诉SpringBoot当前类是一个控制器，可以接收前端请求。交给Spring容器管理
@RestController //默认返回时会经过视图解析器
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/selectAll")
    public List<User> selectAll() {
        return userService.selectAll();
    }

    @RequestMapping("/selectUserScore")
    public List<Map<String, Object>> selectUserScore() {
        return userService.selectUserScore();
    }
}

