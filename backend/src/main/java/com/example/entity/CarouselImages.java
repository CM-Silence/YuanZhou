package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarouselImages {

    //id
    private int id;

    //图片url
    private String imageUrl;

    //描述
    private String description;

    //状态，用于启动禁用
    private Integer state;
}
