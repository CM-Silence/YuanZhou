package com.example.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("resources")
@Setter
public class Resources {

    @TableField("img")
    private String img1;

    @TableField("title")
    private String title;

    @TableField("major")
    private String major;

    @TableField("administrator")
    private String administrator;

    @TableField("view_amount")
    private Integer viewAmount;

    @TableField("like_amount")
    private Integer likeAmount;

    @TableField("collect_amount")
    private Integer collectAmount;

    @TableField("content")
    private String content;

    @TableField("files")
    public String files1;

    @TableField("type")
    private Integer type;

    @TableField(value = "created_at",fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @TableField(value = "update_at",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableId(value = "rid", type = IdType.AUTO)
    private Integer rid;

}
