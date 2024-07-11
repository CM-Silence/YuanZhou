package com.example.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("devices")
public class Devices {

    @TableId(value = "did", type = IdType.ASSIGN_ID)
    private String did;

    @TableField("name")
    private String name;

    @TableField("img1")
    private String img1;

    @TableField("type")
    private String type;

    @TableField("software_system")
    private String software_system;

    @TableField("version")
    private String version;

    @TableField("supplier")
    private String supplier;

    @TableField("device_usage")
    private String device_usage;

    @TableField("lab")
    private String lab;

    @TableField("status")
    private Integer status;

    @TableField(value = "created_at",fill = FieldFill.INSERT)
    private LocalDateTime created_at;

    @TableField(value = "update_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime update_at;
}
