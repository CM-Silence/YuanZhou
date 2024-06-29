package com.example.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.JWTUtils;
import com.example.common.Result;
import com.example.entity.News;
import com.example.entity.User;
import com.example.service.Impl.UserServiceImpl;
import com.example.service.NewsService;
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

    @Autowired
    private NewsService newsService;


    /**
     * 用户登录
     * @param user 用户
     * @return token
     */
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

    /**
     * JWT令牌测试
     * @param request 1
     * @return 1
     */
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

    /**
     * 注册
     * @param username 用户账号
     * @param password 用户密码
     * @return 注册信息
     */
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

    @GetMapping("/news/page")
    public Result<Page> page(int page, int pageSize, String name){
        log.info("page = {}, pageSize = {}, name = {}", page, pageSize, name);

        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);

        //构造条件构造器
        LambdaQueryWrapper<News> queryWrapper = new LambdaQueryWrapper<>();

        //添加模糊查询，使用like进行模糊查询
        queryWrapper.like(name != null, News::getTitle, name);

        //添加排序条件
        queryWrapper.orderByAsc(News::getCreateTime);

        //执行分页查询
        newsService.page(pageInfo, queryWrapper);
        return Result.success(pageInfo);
    }


}

