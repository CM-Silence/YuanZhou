package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Course {

    //课程id，自增
    private Integer courseId;

    //课程名称
    private String name;

    //课程描述
    private String description;

    //课程时长，单位可以是小时或者分钟
    private Integer duration;

    //课程开始时间
    private Date startTime;

    //课程结束时间
    private Date endTime;

    //外键，引用user表uid，课程负责人
    private User courseLeaderId;
}
