package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("news")
public class News implements Serializable {


    //标识+8位唯一索引(主键)
    @TableField("mid")
    private String mid;

    //数据库内置索引
    @TableField("id")
    private Integer id;

    //浏览量
    @TableField("view_amount")
    private Integer viewAmount;

    //消息来源
    @TableField("src")
    private String src;

    //编辑者
    @TableField("editor")
    private String editor;

    //消息内容
    @TableField("content")
    private String content;

    //类型（0：资讯，1：公告）
    @TableField("type")
    private Integer type;

    //审核通过时间
    @TableField("audited_time")
    private Date auditedTime;

    //创建时间
    @TableField("create_at")
    private Date createdAt;

    //更新时间
    @TableField("updated_at")
    private Date updatedAt;

    //附件列表
    @TableField("files")
    private String files;
}
