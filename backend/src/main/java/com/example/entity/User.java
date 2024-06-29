package com.example.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class User implements Serializable {
    private final static Long Serializable = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //id(学校编号、或者个人编号)
    private String uid;

    //学生姓名/教师姓名/社会人士姓名
    private String name;

    //用户账号
    private String username;

    //用户密码
    private String password;

    //电话号码
    private String phone;

    //邮箱
    private String email;

    //班级(学生用）
    private String className;

    //职业类型(社会人士注册用）
    private String occuptionalType;

    //单位类型(社会人士注册用）
    private String unitType;

    //所在单位(社会人士注册用）
    private String unit;

    //token
    private String token;

    //status 状态，也就是权限，不同数字有不同的权限含义
    private Integer status;

    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    //最后登录时间
    private Date loginTime;
}
