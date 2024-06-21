package com.example.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Score {
    private Integer id;
    private String project;
    private Integer score;
    private Integer studentid;

    public Score() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", project='" + project + '\'' +
                ", score=" + score +
                ", studentid='" + studentid + '\'' +
                '}';
    }
}

