package com.example.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("employee")
public class Employee {
    @TableId(value = "sid", type = IdType.ASSIGN_ID)
    private String sid;

    @TableField("name")
    private String name;

    @TableField("phone")
    private String phone;

    @TableField("department")
    private String department;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime created_at;

    @TableField(value = "update_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime update_at;
}
