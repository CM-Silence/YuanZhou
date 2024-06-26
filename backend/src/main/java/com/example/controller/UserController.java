package com.example.controller;

import com.example.common.Result;
import com.example.dto.RegisterDto;
import com.example.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//告诉SpringBoot当前类是一个控制器，可以接收前端请求。交给Spring容器管理
@RestController //默认返回时会经过视图解析器
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public Result<String> login(HttpServletRequest httpServletRequest) {
        log.info("访问成功");
        return Result.success("登录成功");
    }

    //将注册信息用dto接受，方便处理
    @GetMapping("/register")
    public Result<String> register(RegisterDto registerDto) {
        log.info("访问成功");
        return Result.success("注册成功");
    }
}

