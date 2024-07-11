package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;
import com.example.entity.User;
import com.example.service.Impl.UserServiceImpl;
import com.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;
@RestController //默认返回时会经过视图解析器
@RequestMapping("/api/user")
@Slf4j
@CrossOrigin
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserService userService;

    /**
     * 用户分页查询
     * @param key_word 用户名
     * @param page_size 分页大小
     * @param page 当前页面
     * @return 用户列表及分页信息
     */
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> page(@RequestParam(required = false)String key_word,
                                                    @RequestParam(value = "page_size", defaultValue = "65535") Integer page_size,
                                                    @RequestParam(value = "page", defaultValue = "1") Integer page){
        //构造分页构造器
        Page<User> pageInfo = new Page<>(page, page_size);

        //条件构造器
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        //添加过滤条件，使用like关键字
        queryWrapper.like(key_word != null, User::getName , key_word);

        //添加排序条件
        queryWrapper.orderByDesc(User::getUid);

        //执行分页查询
        userService.page(pageInfo, queryWrapper);

        //更改返回格式，与前端对接
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("rows", pageInfo.getRecords()); // 将records更改为rows
        responseData.put("total", pageInfo.getTotal());
        responseData.put("total_pages", (int) Math.ceil((double) pageInfo.getTotal() / pageInfo.getSize()));
        responseData.put("current", pageInfo.getCurrent());
        responseData.put("page", pageInfo.getPages());

        //返回数据
        return ResponseEntity.ok(responseData);
    }

    /**
     * 用户登录
     *
     * @param user 用户
     * @return token
     */
    @PostMapping("/login")
    public Map<String, Object> login(@ModelAttribute User user) {
        log.info("用户名：[{}]", user.getUsername());
        log.info("密码：[{}]", user.getPassword());

        Map<String, Object> map = new HashMap<>();
        try {
            User userDB = userServiceImpl.login(user);
            //生成jwt令牌
            map.put("msg", "login success");
            map.put("code", 201);
            map.put("data", userDB);


        } catch (Exception e) {
            map.put("code", 500);
            map.put("msg", e.getMessage());
        }
        return map;
    }

    /**
     * 注册
     *
     * @param username 用户账号
     * @param password 用户密码
     * @return 注册信息
     */
    @PostMapping("/register")
    public Map<String, Object> register(@RequestParam String username,
                                        @RequestParam String password,
                                        @RequestParam(required = false) Integer permission,
                                        @RequestParam(required = false) String name,
                                        @RequestParam(required = false) String phone,
                                        @RequestParam(required = false) String email,
                                        @RequestParam(required = false) String class_name,
                                        @RequestParam(required = false) String occupational_type) {
        if (permission == null){
            permission = 0;
        }
        User user = userService.findByUserName(username);
        if (user == null) {
            userService.register(username, password, permission, name, phone, email, class_name, occupational_type);
            Map<String, Object> successResponse = new HashMap<>();
            successResponse.put("msg", "register success");
            successResponse.put("code", 201);
            return successResponse;
        } else {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("msg", "the username is occupied, please re-register");
            errorResponse.put("code", 400);
            return errorResponse;
        }
    }

    /**
     * 用户信息编辑
     * @param user 用户类
     * @return 编辑之后的成功信息
     */
    @PutMapping("/edit")
    public Result<String> edit(User user) {
        userService.updateById(user);
        return Result.success2("edit success");
    }

    /**
     * 用户删除
     * @param uid 用户id
     * @return 删除之后的信息
     */
    @DeleteMapping("/delete")
    public Result<String> delete(String uid) {
        if (uid == null) {
            return Result.error("delete error,please give uid!");
        } else {

            userService.removeById(uid);
        }
        return Result.success2("delete success");
    }

}

