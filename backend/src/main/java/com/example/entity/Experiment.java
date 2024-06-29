package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Experiment {

    //实验id，主键，自增
    private Integer experimentId;

    //实验名称
    private String name;

    //实验描述
    private String description;

    //外键，引用course表id
    private Course courseId;

    //实验类型
    private String type;

    //实验文件路径或url
    private String filePath;
}
