package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface NewsMapper extends BaseMapper<News> {
    List<News> selectAll();
}
