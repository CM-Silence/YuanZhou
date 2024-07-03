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
public class TrainingRecord {

    //记录id
    private Integer recordId;

    //学生id，外键，引用user表
    private User studentId;

    //实验id，外键，引用experiment表
    private Experiment experimentId;

    //实训成绩
    private Integer score;

    //实训时间
    private Date trainingTime;

    //实训老师id，外键，引用user表
    private User teacherId;
}
