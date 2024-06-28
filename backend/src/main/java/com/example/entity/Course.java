package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToOne
    @JoinColumn(name = "courseLeaderId")
    private User courseLeaderId;
}
