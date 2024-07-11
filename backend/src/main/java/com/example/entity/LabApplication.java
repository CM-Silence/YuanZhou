package com.example.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
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

    @TableField(value = "created_at",fill = FieldFill.INSERT)
    private LocalDateTime created_at;

    @TableField(value = "update_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime update_at;

    @TableField("audited_time")
    private LocalDateTime audited_time;

    @TableField("comment")
    private String comment;
}
