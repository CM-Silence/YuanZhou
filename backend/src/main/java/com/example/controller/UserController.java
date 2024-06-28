package com.example.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.common.JWTUtils;
import com.example.common.Result;
import com.example.entity.User;
import com.example.service.Impl.UserServiceImpl;
import com.example.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//告诉SpringBoot当前类是一个控制器，可以接收前端请求。交给Spring容器管理
@SuppressWarnings("{all}")
@RestController //默认返回时会经过视图解析器
@RequestMapping("/user")
@Slf4j

public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Map<String, Object> login(User user){
        log.info("用户名：[{}]", user.getUsername());
        log.info("密码：[{}]", user.getPassword());
        Map<String, Object> map = new HashMap<>();
            try {
                User userDB = userServiceImpl.login(user);
                Map<String, String> payload = new HashMap<>();
                payload.put("id", userDB.getUid());
                payload.put("username", userDB.getUsername());
                //生成jwt令牌
                String token = JWTUtils.getToken(payload);
                map.put("state", true);
                map.put("msg", "认证成功!");
                map.put("token", token);  //响应token
            } catch (Exception e) {
                map.put("state", false);
                map.put("msg", e.getMessage());
            }

        return map;
    }

    @PostMapping("/test")
    public Map<String,Object> test(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();

        /**
         * 验证令牌交给拦截器
         * 这里只需要处理自己的业务逻辑
         */
        String token = request.getHeader("token");
        DecodedJWT verify = JWTUtils.verify(token);
        log.info("用户uid：【{}】", verify.getClaim("uid").asString());
        log.info("用户姓名：【{}】", verify.getClaim("name").asString());
        map.put("state", true);
        map.put("msg", "请求成功");
        return map;
    }

    @PostMapping("/register")
    public Result register(@RequestParam String username,@RequestParam String password) {
        User user = userService.findByUserName(username);
        if (user == null) {
            userService.register(username, password);
            return Result.success("注册成功");
        } else {
            return Result.error("用户名被占用，请重新注册");
        }
    }

}

