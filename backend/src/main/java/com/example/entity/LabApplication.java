package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("lab_application")
public class LabApplication {

    @TableId("aid")
    private Integer aid;

    @TableField("audited_type")
    private Integer audited_type;

    @TableField("lab")
    private String lab;

    @TableField("applicant")
    private String applicant;

    @TableField("phone")
    private String phone;

    @TableField("created_at")
    private LocalDateTime created_at;

    @TableField("audited_time")
    private LocalDateTime audited_time;

    @TableField("comment")
    private String comment;
}
