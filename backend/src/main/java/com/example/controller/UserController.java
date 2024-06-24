package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import com.example.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//告诉SpringBoot当前类是一个控制器，可以接收前端请求。交给Spring容器管理
@RestController //默认返回时会经过视图解析器
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public ResponseEntity<String> login(HttpServletRequest httpServletRequest) {
        log.info("访问成功");
        return null;
    }
}

