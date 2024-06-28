package com.example.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "resource")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //资源id
    private Long resourceId;

    //资源名称
    private String name;

    //资源描述
    private String description;

    //资源类型
    @Enumerated(EnumType.STRING) // 如果type是字符串类型，否则可以用ORDINAL
    private ResourceType type; // 假设有一个ResourceType枚举类

    //上传时间
    private LocalDateTime uploadTime;

    //外键，引用user表的uid
    @ManyToOne
    @JoinColumn(name = "uploaderId")
    private User uploaderId; // 关联用户表

    //资源文件路径或url
    private String filePath;

    //状态，表示是否对前台用户可见
    private Integer state;
}

// 假设的ResourceType枚举类
enum ResourceType {
    documents, video, audio, image
}
