package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "carousel_images")
public class CarouselImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //id
    private int id;

    //图片url
    private String imageUrl;

    //描述
    private String description;

    //状态，用于启动禁用
    private Integer state;
}
