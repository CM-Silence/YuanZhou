package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("user")
public class User implements Serializable {
    private final static Long Serializable = 1L;

    //uid(学校编号、或者个人编号)
    @TableField("uid")
    private String uid;

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
    @TableField("phone")
    private String email;

    //班级(学生用）
    @TableField("class_name")
    private String className;

    //职业类型(社会人士注册用）
    @TableField("occupational_type")
    private String occupationalType;

    //单位类型(社会人士注册用）
    @TableField("unit_type")
    private String unitType;

    //所在单位(社会人士注册用）
    @TableField("unit")
    private String unit;

    //token
    @TableField("token")
    private String token;

    //status 状态，也就是权限，不同数字有不同的权限含义
    //0 社会人士，1 学生， 2 教师， 3 管理员
    @TableField("permission")
    private Integer permission;

    //创建时间
    @TableField("create_time")
    private LocalDateTime createTime;

    //最后登录时间
    @TableField("login_time")
    private Date loginTime;
}
