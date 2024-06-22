package com.example.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.time.LocalDateTime;

public class Person {
    //id(个人编号）
    private String uid;

    //社会人士个人姓名
    private String personName;

    //用户账号
    private String username;

    //用户密码
    private String password;

    //电话号码
    private Integer phone;

    //职业类型
    private String occuptionalType;

    //单位类型
    private String unitType;

    //所在单位
    private String unit;

    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    //最后登录时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime loginTime;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
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

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return "Person{" +
                "uid='" + uid + '\'' +
                ", personName='" + personName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone=" + phone +
                ", occuptionalType='" + occuptionalType + '\'' +
                ", unitType='" + unitType + '\'' +
                ", unit='" + unit + '\'' +
                ", createTime=" + createTime +
                ", loginTime=" + loginTime +
                '}';
    }
}
