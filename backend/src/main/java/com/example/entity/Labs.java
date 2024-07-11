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
@TableName("labs")
public class Labs {

    @TableId("lid")
    private Integer lid;

    @TableField("img1")
    private String img1;

    @TableField("img")
    private String name;

    @TableField("img")
    private String workstation_amount;

    @TableField("img")
    private String area;

    @TableField("img")
    private String facility_value;

    @TableField("img")
    private String address;

    @TableField("img")
    private String devices_amount;

    @TableField("img")
    private Integer administrator;

    @TableField("img")
    private String phone;

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime created_at;

    @TableField(value = "update_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime update_at;
}
