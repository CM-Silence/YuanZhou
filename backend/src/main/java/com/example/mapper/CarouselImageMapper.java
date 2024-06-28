package com.example.mapper;

import com.example.entity.Resource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarouselImageMapper {
    List<Resource> findAll();
}
