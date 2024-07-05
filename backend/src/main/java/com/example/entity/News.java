package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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

    @TableField("audited_time")
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

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @TableField("mid")
    private String mid;

    @TableField("id")
    private Integer id;


}
