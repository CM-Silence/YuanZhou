package com.example.controller;

import com.example.common.Result;
import com.example.entity.CarouselImages;
import com.example.mapper.CarouselImageMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.genid.GenId;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carousel")
public class CarouselImageController {

    private final CarouselImageMapper carouselImageMapper;

    public CarouselImageController(CarouselImageMapper carouselImageMapper) {
        this.carouselImageMapper = carouselImageMapper;
    }

    @GetMapping("/get")
    public List<String> getCarouselImageUrls() {
        //从数据库当中获取CarouselImage列表
        List<CarouselImages> resourceList = carouselImageMapper.findAll();

        //提取出ImageUrl字段组成数组（或列表）
        List<String> imageUrls = resourceList.stream()
                .map(CarouselImages::getImageUrl)
                .collect(Collectors.toList());

        //返回图片url列表
        return imageUrls;
    }

}
