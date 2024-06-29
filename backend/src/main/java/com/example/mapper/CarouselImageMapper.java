package com.example.mapper;

import com.example.entity.CarouselImages;
import com.example.entity.CarouselImages;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarouselImageMapper {
    List<CarouselImages> findAll();
}
