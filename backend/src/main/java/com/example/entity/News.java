package com.example.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "news")
@Data
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //id，主键
    private Integer id;

    //新闻标题
    private String title;

    //新闻内容
    private String content;

    //发布日期
    @Column(name = "publish_date")
    private String publishDate;

    //新闻分类id，外键，关联到新闻分类表
    @Column(name = "category_id")
    private String categoryId;

    //创建日期，用于排序
    private Date createTime;
}
