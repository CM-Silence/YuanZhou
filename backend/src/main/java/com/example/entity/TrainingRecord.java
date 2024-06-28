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
@Table(name = "training_record")
public class TrainingRecord {

    //记录id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recordId;

    //学生id，外键，引用user表
    @ManyToOne
    @JoinColumn(name = "user")
    private User studentId;

    //实验id，外键，引用experiment表
    @ManyToOne
    @JoinColumn(name = "experiment")
    private Experiment experimentId;

    //实训成绩
    private Integer score;

    //实训时间
    private Date trainingTime;

    //实训老师id，外键，引用user表
    @ManyToOne
    @JoinColumn(name = "user")
    private User teacherId;
}
