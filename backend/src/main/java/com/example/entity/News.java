package com.example.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("news")
public class News {

    @TableField("img")
    private String img;

    @TableField("title")
    private String title;

    @TableField(value = "audited_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime auditedTime;

    @TableField("src")
    private String src;

    @TableField("editor")
    private String editor;

    @TableField("view_amount")
    private Integer viewAmount;

    @TableField("content")
    private String content;

    @TableField("type")
    private Integer type;

    @TableField(value = "created_at",fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @TableField(value = "update_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("mid")
    private Integer mid;

}
