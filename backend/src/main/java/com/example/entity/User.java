package com.example.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.util.Date;

public class User {
    //id(学校编号、或者个人编号)
    private String uid;

    //学生姓名/教师姓名/社会人士姓名
    private String name;

    //用户账号
    private String username;

    //用户密码
    private String password;

    //电话号码
    private Integer phone;

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

    @Transient//声明这个字段不需要和数据库对应
    private String token;

    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    //最后登录时间
    private Date loginTime;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getOccuptionalType() {
        return occuptionalType;
    }

    public void setOccuptionalType(String occuptionalType) {
        this.occuptionalType = occuptionalType;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", className='" + className + '\'' +
                ", occuptionalType='" + occuptionalType + '\'' +
                ", unitType='" + unitType + '\'' +
                ", unit='" + unit + '\'' +
                ", createTime=" + createTime +
                ", loginTime=" + loginTime +
                '}';
    }
}
