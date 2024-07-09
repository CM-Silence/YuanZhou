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
    private String img1;

    @TableField("title")
    private String title;

    @TableField(value = "audited_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime audited_time;

    @TableField("src")
    private String src;

    @TableField("editor")
    private String editor;

    @TableField("view_amount")
    private Integer view_amount;

    @TableField("content")
    private String content;

    @TableField("files")
    private String files1;

    @TableField("type")
    private Integer type;

    @TableField(value = "created_at",fill = FieldFill.INSERT)
    private LocalDateTime created_at;

    @TableField(value = "update_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime update_at;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("mid")
    private Integer mid;

}
