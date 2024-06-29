package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class News implements Serializable {

    private static final long serialVersionUID = 1L;
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //id，主键
    private Integer id;

    //新闻标题
    private String title;

    //新闻内容
    private String content;

    @TableField("publish_date")
    //发布日期
    private String publishDate;

    @TableField("category_id")
    //新闻分类id，外键，关联到新闻分类表
    private String categoryId;

    //创建日期，用于排序
    private LocalDateTime createTime;
}
