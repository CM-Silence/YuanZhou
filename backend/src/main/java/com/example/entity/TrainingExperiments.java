package com.example.entity;

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
@TableName("training_experiments")
public class TrainingExperiments {
    @TableId("tid")
    private Integer tid;

    @TableField("img")
    private String img1;

    @TableField("name")
    private String name;

    //专业
    @TableField("major")
    private String major;

    //类别（实验类别）
    @TableField("type")
    private String type;

    //浏览量
    @TableField("view_amount")
    private Integer view_amount;

    //点赞量
    @TableField("like_amount")
    private Integer like_amount;

    //收藏量
    @TableField("collect_amount")
    private Integer collect_amount;

    //附件
    @TableField("files")
    private String files1;

    //对接证书
    @TableField("certificate")
    private String certificate;

    //对接竞赛
    @TableField("docking_contest")
    private String docking_contest;

    //课时数
    @TableField("class_hour")
    private Integer class_hour;

    //描述
    @TableField("description")
    private String description;

    //创建时间
    @TableField("created_at")
    private LocalDateTime created_at;

    //更新时间
    @TableField("update_at")
    private LocalDateTime update_at;

}
