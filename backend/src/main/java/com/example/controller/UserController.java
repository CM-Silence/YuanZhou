package com.example.controller;

import com.example.entity.Student;
import com.example.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Map;

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

