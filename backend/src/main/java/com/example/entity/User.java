package com.example.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@TableName("user")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    //uid(学校编号、或者个人编号)
    @TableId("uid")
    private Integer uid;

    //学生姓名/教师姓名/社会人士姓名
    @TableField("name")
    private String name;

    //用户账号
    @TableField("username")
    private String username;

    //用户密码
    @TableField("password")
    private String password;

    //电话号码
    @TableField("phone")
    private String phone;

    //邮箱
    @TableField("email")
    private String email;

    //班级(学生用）
    @TableField("class_name")
    private String class_name;

    //职业类型(社会人士注册用）
    @TableField("occupational_type")
    private String occupational_type;

    //单位类型(社会人士注册用）
    @TableField("unit_type")
    private String unit_type;

    //所在单位(社会人士注册用）
    @TableField("unit")
    private String unit;

    //创建时间
    @TableField(value = "created_at",fill = FieldFill.INSERT)
    private LocalDateTime created_at;

    //更新时间
    @TableField("update_at")
    private LocalDateTime update_at;

    //token
    @TableField("token")
    private String token;

    //0 社会人士，1 学生， 2 教师， 3 管理员
    @TableField("permission")
    private Integer permission;
}
